package no.jore.core.domain;

public class ShortenedUrlEntityMapper {
  public static ShortUrlEntity toEntity(ShortUrl shortUrl) {
    return new ShortUrlEntity(
      shortUrl.getId(),
      shortUrl.getUrl(),
      shortUrl.getShortened(),
      shortUrl.getCreatedAt(),
      shortUrl.getVisitCount()
    );
  }

  public static ShortUrl toDomain(ShortUrlEntity shortUrlEntity) {
    return new ShortUrl(
      shortUrlEntity.getId(),
      shortUrlEntity.getUrl(),
      shortUrlEntity.getShortened(),
      shortUrlEntity.getCreatedAt(),
      shortUrlEntity.getVisitCount()
    );
  }
}
