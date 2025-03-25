package org.kammerer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        RelocationRequest request =
                new RelocationRequest("2025-04-01T08:00", "Max Mustermann", "Berlin", "München", true, 3, true);

        when(relocationRequestService.createRelocationRequest(request)).thenReturn(request);

        mockMvc.perform(post("/requestForRelocationSupport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Relocation request successfully created"));
    }
}