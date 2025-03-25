package org.kammerer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RelocationRequestApiTests {

    @Autowired
    private MockMvc mockMvc;

    final private ObjectMapper objectMapper;

    @Autowired
    private RelocationRequestRepository relocationRequestRepository;

    public RelocationRequestApiTests() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void createRelocationRequest_Success() throws Exception {
        RelocationRequest request =
                new RelocationRequest("2025-04-01T08:00", "Max Mustermann", "Berlin", "München", true, 3, true);

        mockMvc.perform(post("/requestForRelocationSupport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Relocation request successfully created"));

        Optional<RelocationRequest> savedRequest = relocationRequestRepository.findById(1);
        assertThat(savedRequest).isPresent();
        assertThat(savedRequest.get().getName()).isEqualTo("Max Mustermann");
    }
}