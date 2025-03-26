package org.kammerer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RelocationRequestComponentTests {

    @Autowired
    private MockMvc mockMvc;

    final private ObjectMapper objectMapper;

    @MockBean
    private RelocationRequestService relocationRequestService;

    public RelocationRequestComponentTests() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createRelocationRequest_ComponentTest() throws Exception {
        RelocationRequest request = new RelocationRequest();
        request.setDatetime(LocalDateTime.parse("2025-04-01T08:00"));
        request.setName("Max Mustermann");
        request.setFromLocation("Wien");
        request.setToLocation("Graz");
        request.setFloor(3);
        request.setElevator(true);
        request.setPackagingService(true);

        String jsonRequest = """
        {
            "datetime": "2025-04-01T08:00",
            "name": "Max Mustermann",
            "fromLocation": "Wien",
            "toLocation": "Graz",
            "floor": 3,
            "elevator": true,
            "packagingService": true
        }
        """;

        when(relocationRequestService.createRelocationRequest(request)).thenReturn(request);

        mockMvc.perform(post("/requestForRelocationSupport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Relocation request successfully created"));
    }
}