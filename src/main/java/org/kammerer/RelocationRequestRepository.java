package org.kammerer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelocationRequestRepository extends JpaRepository<RelocationRequest, Integer> {}
