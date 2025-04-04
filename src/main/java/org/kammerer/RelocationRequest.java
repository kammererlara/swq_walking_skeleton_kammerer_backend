package org.kammerer;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RelocationRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private LocalDateTime datetime;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String fromLocation;

  @Column(nullable = false)
  private String toLocation;

  private boolean elevator;
  private int floor;
  private boolean packagingService;

  public RelocationRequest() {}
}
