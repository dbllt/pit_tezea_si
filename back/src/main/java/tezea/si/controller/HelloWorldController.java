package tezea.si.controller;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import tezea.si.utils.FileUploadUtil;

/**
 * A test controller to remove before production (Used in tests)
 * 
 * @author Nils Richard
 *
 */
@RestController
public class HelloWorldController {

    Logger logger = LogManager.getLogger(getClass());

    @Operation(summary = "A very polite endpoint")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "A simple hello with your name in it") })
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return "Hello " + auth.getName();
    }

    @Operation(summary = "Test to upload photos")
    @ApiResponses(value = { @ApiResponse(responseCode = "201", description = "A simple upload") })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadPhoto(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        logger.debug("Uploading a photo");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String uploadDir = "user-photos/";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
