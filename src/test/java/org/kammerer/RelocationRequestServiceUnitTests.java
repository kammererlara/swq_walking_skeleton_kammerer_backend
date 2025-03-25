package org.kammerer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class RelocationRequestServiceUnitTests {

    @Mock
    private RelocationRequestRepository relocationRequestRepository;

    @InjectMocks
    private RelocationRequestService relocationRequestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRelocationRequest_UnitTest() {
        RelocationRequest request =
                new RelocationRequest("2025-04-01T08:00", "Max Mustermann", "Berlin", "München", true, 3, true);

        when(relocationRequestRepository.save(request)).thenReturn(request);

        RelocationRequest savedRequest = relocationRequestService.createRelocationRequest(request);

        assertThat(savedRequest).isNotNull();
        assertThat(savedRequest.getName()).isEqualTo("Max Mustermann");
    }
}