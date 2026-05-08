package no.jore.core.application.mapper;

import no.jore.core.application.in.ShortUrlDTO;
import no.jore.core.domain.ShortUrl;

import java.util.UUID;

public class ShortUrlDTOMapper {

  public static ShortUrl toDomain(ShortUrlDTO shortUrlDTO, UUID newId) {
    return new ShortUrl(
      newId,
      shortUrlDTO.getUrl(),
      shortUrlDTO.getShortened(),
      shortUrlDTO.getCreatedAt(),
      shortUrlDTO.getVisitCount()
    );
  }
}
