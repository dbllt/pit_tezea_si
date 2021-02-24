package tezea.si.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.dto.SmallRequestSearchDTO;
import tezea.si.service.EntityCreationFromDTOService;
import tezea.si.service.EntityToDTOService;
import tezea.si.service.SmallRequestSearchService;
import tezea.si.utils.FileUploadUtil;

@RestController
@RequestMapping(value = "/requests")
public class RequestController {
    Logger logger = LogManager.getLogger(getClass());

    @Autowired
    SmallRequestDAO dao;

    @Autowired
    SmallClientDAO clientDao;

    @Autowired
    UserTezeaDAO userDao;

    @Autowired
    EntityCreationFromDTOService creator;

    @Autowired
    EntityToDTOService toDTO;

    @Autowired
    SmallRequestSearchService searchService;

    @Operation(summary = "Search for requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of requests corresponding to your search"),
            @ApiResponse(responseCode = "400", description = "If the search body could not be parsed") })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SmallRequest>> getRequests(@RequestBody SmallRequestSearchDTO search) {
        Specification<SmallRequest> spec = searchService.convert(search);
        return ResponseEntity.ok(dao.findAll(spec));
    }

    @Operation(summary = "Get one request by id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The request with this id"),
            @ApiResponse(responseCode = "404", description = "If there is no request with this id") })
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SmallRequest> getRequest(@PathVariable Long id) {
        Optional<SmallRequest> request = dao.findById(id);
        if (request.isPresent()) {
            return ResponseEntity.ok(request.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a request")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "The new request"),
            @ApiResponse(responseCode = "400", description = "If the input request body could not be parsed") })
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<SmallRequestDTO> createRequest(@RequestBody SmallRequest request) {
        SmallRequest entity = creator.convertToEntity(request);

        SmallRequestDTO response = toDTO.convertToDTO(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @Operation(summary = "Update a request")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "The request updated"),@ApiResponse(responseCode = "204", description = "If there is no request with this id"),
            @ApiResponse(responseCode = "400", description = "If the input request body could not be parsed") })
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<SmallRequestDTO> updateRequest(@RequestBody SmallRequest request) {
        Optional<SmallRequest> req = dao.findById(request.getId());
        if (!req.isPresent())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        
        SmallRequest entity = creator.convertToEntity(request);
        
        SmallRequestDTO response = toDTO.convertToDTO(entity);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Upload images for a request")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Images have been uploaded"),
            @ApiResponse(responseCode = "404", description = "If there is no request with this id"),
            @ApiResponse(responseCode = "400", description = "If the input request body could not be parsed or if images are not images") })
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImages(@PathVariable Long id, @RequestParam("images") List<MultipartFile> files) {
        Optional<SmallRequest> request = dao.findById(id);
        if (!request.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        // Allows only images to be uploaded

        List<String> acceptableExtentions = List.of("png", "jpg", "jpeg");

        for (MultipartFile multipartFile : files) {
            Optional<String> ext = getExtension(multipartFile.getOriginalFilename());
            if (ext.isEmpty() || !acceptableExtentions.contains(ext.get()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<String> photosUrl = new ArrayList<String>(request.get().getPhotos());

        for (MultipartFile multipartFile : files) {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            String uploadDir = "user-photos/" + id + "/";

            fileName = getUniqueFileName(photosUrl, fileName, uploadDir);

            try {
                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            photosUrl.add("/" + uploadDir + fileName);
        }

        request.get().setPhotos(photosUrl);

        dao.save(request.get());

        return ResponseEntity.ok().build();
    }

    private String getUniqueFileName(List<String> photosUrl, String fileName, String uploadDir) {
        String fileNameWithoutExt = removeFileExtension(fileName);
        String ext = "." + getExtension(fileName).get();

        int i = 0;
        while (photosUrl.contains("/" + uploadDir + fileName)) {
            fileName = fileNameWithoutExt + ++i + ext;
        }
        return fileName;
    }

    public static String removeFileExtension(String filename) {
        if (filename == null || filename.isEmpty())
            return filename;
        String extPattern = "(?<!^)[.]" + ".*";
        return filename.replaceAll(extPattern, "");
    }

    public Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename).filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}