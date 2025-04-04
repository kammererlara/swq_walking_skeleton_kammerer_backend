package org.kammerer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RelocationRequestApiTests {

  @Autowired private MockMvc mockMvc;

  @Autowired private RelocationRequestRepository relocationRequestRepository;

  @Test
  void createRelocationRequest_Success() throws Exception {
    String jsonRequest =
        """
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

    mockMvc
        .perform(
            post("/requestForRelocationSupport")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.message").value("Relocation request successfully created"));

    Optional<RelocationRequest> savedRequest = relocationRequestRepository.findById(1);
    assertThat(savedRequest).isPresent();
    assertThat(savedRequest.get().getName()).isEqualTo("Max Mustermann");
  }
}
