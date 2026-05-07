package no.jore.core.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import no.jore.core.application.in.ShortUrlDTO;
import no.jore.core.application.mapper.ShortUrlMapper;
import no.jore.core.domain.ShortUrlEntity;
import no.jore.infrastructure.ShortUrlPanacheRepository;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class ShortenedUrlService {
  private final static Logger Log = Logger.getLogger("ShortenedUrlService");
  private final ShortUrlPanacheRepository shortUrlPanacheRepository;

  @Inject
  public ShortenedUrlService(ShortUrlPanacheRepository shortUrlPanacheRepository) {
    this.shortUrlPanacheRepository = shortUrlPanacheRepository;
  }

  public void shortenUrl(ShortUrlDTO shortUrlDTO) {
    ShortUrlEntity entity = ShortUrlMapper.toEntity(shortUrlDTO);

    shortUrlPanacheRepository.persist(entity);
    Log.info("shortened url " + shortUrlDTO.getUrl());
  }

  public List<ShortUrlDTO> getAll() {
    return shortUrlPanacheRepository.getAll();
  }
}
