package org.kammerer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class RelocationRequestControllerUnitTests {

  @Mock private RelocationRequestService relocationRequestService;

  @InjectMocks private RelocationRequestController relocationRequestController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void createRelocationRequest_ControllerUnitTest() {
    RelocationRequest request = new RelocationRequest();
    request.setDatetime(LocalDateTime.parse("2025-04-01T08:00"));
    request.setName("Max Mustermann");
    request.setFromLocation("Wien");
    request.setToLocation("Graz");
    request.setFloor(3);
    request.setElevator(true);
    request.setPackagingService(true);

    when(relocationRequestService.createRelocationRequest(request)).thenReturn(request);

    ResponseEntity<?> response = relocationRequestController.createRelocationRequest(request);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
  }
}
