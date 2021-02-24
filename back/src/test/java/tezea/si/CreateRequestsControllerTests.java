package tezea.si;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import tezea.si.dao.SmallClientDAO;
import tezea.si.dao.SmallEstimationDAO;
import tezea.si.dao.SmallRequestDAO;
import tezea.si.dao.UserTezeaDAO;
import tezea.si.model.SmallClientDTO;
import tezea.si.model.SmallEstimationDTO;
import tezea.si.model.SmallRequestDTO;
import tezea.si.model.SmallUserDTO;
import tezea.si.model.business.ClientType;
import tezea.si.model.business.HonorificTitle;
import tezea.si.model.business.Site;
import tezea.si.model.business.SmallClient;
import tezea.si.model.business.UserTezea;
import tezea.si.model.business.request.Priority;
import tezea.si.model.business.request.RequestStatus;
import tezea.si.model.business.request.SatisfactionLevel;
import tezea.si.model.business.request.Service;
import tezea.si.model.business.request.SmallEstimation;
import tezea.si.model.business.request.SmallRequest;
import tezea.si.model.business.request.TimeUnit;
import tezea.si.model.business.request.Tool;
import tezea.si.model.business.request.Vehicle;
import tezea.si.utils.TestUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateRequestsControllerTests {

    private static final String REQUESTS_URL = "/requests";

    @Autowired
    UserTezeaDAO userDao;

    @Autowired
    SmallRequestDAO requestDao;

    @Autowired
    SmallClientDAO clientDao;

    @Autowired
    SmallEstimationDAO estimationDao;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void before() {
        UserTezea user = new UserTezea();
        user.setUsername("jean");
        user.setPassword("$2a$10$sDP3s/p0M1TCOW6FizwLWulsnwT2BryFkLHqKusRRljaYKYOWVE7u");
        userDao.save(user);

    }

    @AfterEach
    public void destroy() {
        requestDao.deleteAll();
        estimationDao.deleteAll();
        userDao.deleteAll();
    }

    @Test
    public void cannotAccessWithoutToken() throws Exception {
        // Arrange
        SmallRequest request = new SmallRequest();

        // Act
        this.mockMvc.perform(
                post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void createEmptyRequestReturnsRequestAndSetsLastUpdated() throws Exception {
        // Arrange
        SmallRequest request = new SmallRequest();
        SmallRequestDTO expected = new SmallRequestDTO();

        // Act
        String response = this.mockMvc
                .perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        // Assert
        SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
        assertThat(result).usingRecursiveComparison().ignoringFields("id", "lastUpdated").isEqualTo(expected);
        assertThat(result.getId()).isNotZero();
        assertThat(result.getLastUpdated()).isNotNull();
    }

    @Test
    public void cannotSetRequestId() throws Exception {
        // Arrange
        SmallRequest request = new SmallRequest();
        Long attemptedId = 56L;
        request.setId(attemptedId);

        // Act
        String response = this.mockMvc
                .perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        // Assert
        SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
        assertThat(result.getId()).isNotZero();
        assertThat(result.getId()).isNotEqualTo(attemptedId);
    }

    @Test
    public void cannotSetClientId() throws Exception {
        // Arrange
        SmallClient client = new SmallClient();
        client.setLastName("Dula");
        Long attemptedId = 78L;
        client.setId(attemptedId);

        SmallRequest request = new SmallRequest();
        request.setClient(client);

        // Act
        String response = this.mockMvc
                .perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        // Assert
        SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
        assertThat(result.getId()).isNotZero();
        assertThat(result.getId()).isNotEqualTo(attemptedId);
    }

    @Test
    public void createRequestWithClient() throws Exception {
        // Arrange
        String name = "Dula";
        SmallClient client = new SmallClient();
        client.setLastName(name);

        SmallRequest request = new SmallRequest();
        request.setClient(client);

        SmallRequestDTO expected = new SmallRequestDTO();
        SmallClientDTO expectedClient = new SmallClientDTO();
        expectedClient.setLastName(name);
        expected.setClient(expectedClient);

        // Act
        String response = this.mockMvc
                .perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        // Assert
        SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
        assertThat(result).usingRecursiveComparison().ignoringFields("id", "client.id", "lastUpdated")
                .isEqualTo(expected);
        assertThat(result.getId()).isNotZero();
    }

    @Test
    public void createFullRequest() throws Exception {
        // Arrange
        String username = "jean";
        String access = "difficult";
        String description = "some service";
        String address = "45 rue";
        String email = "zet@ok.com";
        String phone = "+330756874512";
        String phone2 = "+330765458452";
        String postCode = "35000";
        String city = "Rennes";
        String companyName = "Gateaux";
        String lastName = "Brindacier";
        String firstName = "Fifi";
        String internal = "check phone number";
        String otherTools = "chair";
        String siret = "10201445";
        HonorificTitle title = HonorificTitle.MME;
        int reps = 5;
        int wood = 12;
        int donated = 50;
        int nbPeople = 3;
        int duration = 4;
        LocalDate date = LocalDate.of(2021, 02, 05);
        LocalTime time = LocalTime.of(15, 20);

        UserTezea user = new UserTezea();
        user.setUsername(username);

        SmallEstimation estimation = new SmallEstimation();
        estimation.setEstimationResponsable(user);
        estimation.setNumberEmployeesNeeded(nbPeople);
        estimation.setToolsNeeded(List.of(Tool.FOR_SERVICE, Tool.SPECIFIC));
        estimation.setOtherTools(otherTools);
        estimation.setVehiclesNeeded(List.of(Vehicle.BENNE));
        estimation.setExpectedDuration(duration);

        SmallClient client = new SmallClient();
        client.setEmail(email);
        client.setPhoneNumber(phone);
        client.setPhoneNumber2(phone2);
        client.setAddress(address);
        client.setPostCode(postCode);
        client.setCity(city);
        client.setCompanyName(companyName);
        client.setSiret(siret);
        client.setLastName(lastName);
        client.setFirstName(firstName);
        client.setHonorificTitle(title);
        client.setType(ClientType.COMPANY);

        SmallRequest request = new SmallRequest();
        request.setAccessDetails(access);
        request.setDescription(description);
        request.setDate(date);
        request.setTime(time);
        request.setRepetitionTime(reps);
        request.setRepetitionUnit(TimeUnit.MONTH);
        request.setStatus(RequestStatus.NEW);
        request.setPriority(Priority.HIGH);
        request.setAmountWood(wood);
        request.setAmountDonated(donated);
        request.setAppointmentPlasmaDate(date);
        request.setSatisfactionLevel(SatisfactionLevel.NOT_SATISFIED);
        request.setType(Service.DONATION);
        request.setInternalInfo(internal);
        request.setSite(Site.COUTURE);

        request.setClient(client);
        request.setResponsable(user);
        request.setClosedBy(user);
        request.setLastUpdatedBy(user);
        request.setEstimation(estimation);

        SmallUserDTO expectedUser = new SmallUserDTO();
        expectedUser.setUsername(username);

        SmallEstimationDTO expectedEstimation = new SmallEstimationDTO();
        expectedEstimation.setEstimationResponsable(expectedUser);
        expectedEstimation.setNumberEmployeesNeeded(nbPeople);
        expectedEstimation.setToolsNeeded(List.of(Tool.FOR_SERVICE, Tool.SPECIFIC));
        expectedEstimation.setOtherTools(otherTools);
        expectedEstimation.setVehiclesNeeded(List.of(Vehicle.BENNE));
        expectedEstimation.setExpectedDuration(duration);

        SmallClientDTO expectedClient = new SmallClientDTO();
        expectedClient.setEmail(email);
        expectedClient.setPhoneNumber(phone);
        expectedClient.setPhoneNumber2(phone2);
        expectedClient.setAddress(address);
        expectedClient.setPostCode(postCode);
        expectedClient.setCity(city);
        expectedClient.setCompanyName(companyName);
        expectedClient.setSiret(siret);
        expectedClient.setLastName(lastName);
        expectedClient.setFirstName(firstName);
        expectedClient.setHonorificTitle(title);
        expectedClient.setType(ClientType.COMPANY);

        SmallRequestDTO expected = new SmallRequestDTO();
        expected.setAccessDetails(access);
        expected.setDescription(description);
        expected.setDate(date);
        expected.setTime(time);
        expected.setRepetitionTime(reps);
        expected.setRepetitionUnit(TimeUnit.MONTH);
        expected.setStatus(RequestStatus.NEW);
        expected.setPriority(Priority.HIGH);
        expected.setAmountWood(wood);
        expected.setAmountDonated(donated);
        expected.setAppointmentPlasmaDate(date);
        expected.setSatisfactionLevel(SatisfactionLevel.NOT_SATISFIED);
        expected.setType(Service.DONATION);
        expected.setInternalInfo(internal);
        expected.setSite(Site.COUTURE);

        expected.setClient(expectedClient);
        expected.setResponsable(expectedUser);
        expected.setClosedBy(expectedUser);
        expected.setLastUpdatedBy(expectedUser);
        expected.setEstimation(expectedEstimation);

        // Act
        String response = this.mockMvc
                .perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        // Assert
        SmallRequestDTO result = mapper.readValue(response, SmallRequestDTO.class);
        assertThat(result)
                .usingRecursiveComparison().ignoringFields("id", "lastUpdated", "client.id", "closedBy.id",
                        "estimation.estimationResponsable.id", "estimation.id", "lastUpdatedBy.id", "responsable.id")
                .isEqualTo(expected);
        assertThat(result.getId()).isNotZero();
        assertThat(result.getLastUpdated()).isNotNull();
    }

    @Test
    public void uploadImage() throws Exception {
        // Arrange
        SmallRequestDTO smallReq = createSimpleRequest();
        String url = REQUESTS_URL + "/" + smallReq.getId();
        String badUrl = REQUESTS_URL + "/105";

        File f = new ClassPathResource("image.jpg").getFile();
        File f2 = new ClassPathResource("image2.jpg").getFile();

        FileInputStream fi1 = new FileInputStream(f);
        FileInputStream fi2 = new FileInputStream(f2);
        MockMultipartFile fstmp = new MockMultipartFile("images", f.getName(), "multipart/form-data", fi1);
        MockMultipartFile fstmp2 = new MockMultipartFile("images", f2.getName(), "multipart/form-data", fi2);

        // Act, assert
        mockMvc.perform(MockMvcRequestBuilders.multipart(url).file(fstmp)
                .headers(TestUtils.userAuthorizationHeader(mockMvc)).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.multipart(url).file(fstmp).file(fstmp2)
                .headers(TestUtils.userAuthorizationHeader(mockMvc)).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());

        // Upload image for a non existent request
        mockMvc.perform(MockMvcRequestBuilders.multipart(badUrl).file(fstmp)
                .headers(TestUtils.userAuthorizationHeader(mockMvc)).contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isNotFound());

        // Verification that images are defined in request
        SmallRequestDTO updatedSmallReq = getSimpleRequest(smallReq.getId());
        assertThat(updatedSmallReq).usingRecursiveComparison().ignoringFields("photos", "lastUpdated")
                .isEqualTo(smallReq);
        assertThat(updatedSmallReq.getId()).isNotZero();
        assertThat(updatedSmallReq.getPhotos()).isNotEmpty();
        assertThat(updatedSmallReq.getPhotos()).hasSize(3);

        // Verification that you can get the images from server
        byte[] firstImage = Files.readAllBytes(f.toPath());

        this.mockMvc
                .perform(get(updatedSmallReq.getPhotos().get(0)).contentType(MediaType.IMAGE_JPEG)
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray().equals(firstImage);

        byte[] secondImage = Files.readAllBytes(f2.toPath());

        this.mockMvc
                .perform(get(updatedSmallReq.getPhotos().get(1)).contentType(MediaType.IMAGE_JPEG)
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray().equals(secondImage);

    }

    private SmallRequestDTO createSimpleRequest() throws Exception {
        SmallRequest request = new SmallRequest();
        String response = this.mockMvc
                .perform(post(REQUESTS_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();

        return mapper.readValue(response, SmallRequestDTO.class);
    }

    private SmallRequestDTO getSimpleRequest(Long id) throws Exception {
        SmallRequest request = new SmallRequest();
        String url = REQUESTS_URL + "/" + id;

        String response = this.mockMvc
                .perform(get(url).content(mapper.writeValueAsString(request))
                        .headers(TestUtils.userAuthorizationHeader(mockMvc)))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        return mapper.readValue(response, SmallRequestDTO.class);
    }

    // todo: test cannot add request with unknown user, or with unknown site
    // todo: test cannot set request state, is new by default
    // todo: test cannot set lastupdated date

}
