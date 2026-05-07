package no.jore.core.application.mapper;

import no.jore.core.application.in.ShortUrlDTO;
import no.jore.core.domain.ShortUrlEntity;

public class ShortUrlMapper {
  public static ShortUrlEntity toEntity(ShortUrlDTO shortUrlDTO) {
    return new ShortUrlEntity(
      shortUrlDTO.getUrl(),
      shortUrlDTO.getShortened(),
      shortUrlDTO.getCreatedAt(),
      shortUrlDTO.getVisitCount()
    );
  }

  public static ShortUrlDTO toDTO(ShortUrlEntity shortUrlEntity) {
    return new ShortUrlDTO(
      shortUrlEntity.getId(),
      shortUrlEntity.getUrl(),
      shortUrlEntity.getShortened(),
      shortUrlEntity.getCreatedAt(),
      shortUrlEntity.getVisitCount()
    );
  }
}
