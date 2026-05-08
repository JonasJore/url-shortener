package no.jore.core.application.in;

import java.time.LocalDateTime;

public class ShortUrlDTO {
  private String url;
  private String shortened;
  private LocalDateTime createdAt;
  private int visitCount;

  public ShortUrlDTO(String url, String shortened, LocalDateTime createdAt, int visitCount) {
    this.url = url;
    this.shortened = shortened;
    this.createdAt = createdAt;
    this.visitCount = visitCount;
  }

  public String getUrl() {
    return this.url;
  }

  public String getShortened() {
    return this.shortened;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public int getVisitCount() {
    return this.visitCount;
  }
}
