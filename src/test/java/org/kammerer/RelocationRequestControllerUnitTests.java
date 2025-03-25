package org.kammerer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RelocationRequestControllerUnitTests {

    @Mock
    private RelocationRequestService relocationRequestService;

    @InjectMocks
    private RelocationRequestController relocationRequestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRelocationRequest_ControllerUnitTest() {
        RelocationRequest request =
                new RelocationRequest("2025-04-01T08:00", "Max Mustermann", "Berlin", "München", true, 3, true);

        when(relocationRequestService.createRelocationRequest(request)).thenReturn(request);

        ResponseEntity<?> response = relocationRequestController.createRelocationRequest(request);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}