package no.jore.core.application.in;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShortUrlDTO {
  private UUID id;
  private String url;
  private String shortened;
  private LocalDateTime createdAt;

  public ShortUrlDTO(UUID id, String url, String shortened, LocalDateTime createdAt) {
    this.id = id;
    this.url = url;
    this.shortened = shortened;
    this.createdAt = createdAt;
  }

  public UUID getId() {
    return this.id;
  }
  public String getUrl() {
    return this.url;
  }
}
