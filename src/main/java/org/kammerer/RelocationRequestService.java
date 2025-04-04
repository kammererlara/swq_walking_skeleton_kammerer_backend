package org.kammerer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelocationRequestService {

  @Autowired private RelocationRequestRepository relocationRequestRepository;

  public RelocationRequest createRelocationRequest(RelocationRequest request) {
    return relocationRequestRepository.save(request);
  }
}
