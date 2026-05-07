package no.jore.core.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shortened_url")
public class ShortUrlEntity extends PanacheEntityBase {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;
  @Column(name = "url", nullable = false, length = 2048)
  private String url;
  @Column(name = "shortened_url", nullable = false)
  private String shortened;
  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;
  @Column(name = "visit_count", nullable = false)
  private int visitCount;

  public ShortUrlEntity() {
  }

  public ShortUrlEntity(String url, String shortened, LocalDateTime createdAt, int visitCount) {
    this.url = url;
    this.shortened = shortened;
    this.createdAt = createdAt;
    this.visitCount = visitCount;
  }

  public UUID getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public String getShortened() {
    return shortened;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public int getVisitCount() {
    return visitCount;
  }
}
