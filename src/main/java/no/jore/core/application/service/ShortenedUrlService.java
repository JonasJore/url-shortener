package no.jore.core.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import no.jore.core.domain.ShortUrl;
import no.jore.ports.out.ShortUrlRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class ShortenedUrlService {
  private final static Logger Log = Logger.getLogger("ShortenedUrlService");
  private final ShortUrlRepositoryPort shortUrlPanacheRepository;

  @Inject
  public ShortenedUrlService(ShortUrlRepositoryPort shortUrlPanacheRepository) {
    this.shortUrlPanacheRepository = shortUrlPanacheRepository;
  }

  public void shortenUrl(ShortUrl shortUrl) {
    shortUrlPanacheRepository.save(shortUrl);
    Log.info("shortened url " + shortUrl.getUrl());
  }

  public List<ShortUrl> getAll() {
    return shortUrlPanacheRepository.getAll();
  }

  public Optional<ShortUrl> findById(String id) {
    return shortUrlPanacheRepository.findById(id);
  }

  @Transactional
  public void handleRedirect(String id) {
    shortUrlPanacheRepository.increaseVisitCount(id);
  }

}
