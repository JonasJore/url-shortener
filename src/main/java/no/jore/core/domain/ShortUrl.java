package no.jore.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class ShortUrl {
  private UUID id;
  private String url;
  private String shortened;
  private LocalDateTime createdAt;
  private int visitCount;

  public ShortUrl(UUID id, String url, String shortened, LocalDateTime createdAt, int visitCount) {
    this.id = id;
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
