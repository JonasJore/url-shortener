package no.jore.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import no.jore.core.domain.ShortUrl;
import no.jore.core.domain.ShortUrlEntity;
import no.jore.core.domain.ShortenedUrlEntityMapper;
import no.jore.ports.out.ShortUrlRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ShortUrlPanacheRepository implements ShortUrlRepositoryPort, PanacheRepository<ShortUrlEntity> {

  public Optional<ShortUrl> findById(String id) {
    Optional<ShortUrlEntity> optionalShortUrl = Optional.ofNullable(
      ShortUrlEntity.findById(UUID.fromString(id))
    );
    return optionalShortUrl.map(ShortenedUrlEntityMapper::toDomain);
  }

  public List<ShortUrl> getAll() {
    return listAll().stream().map(ShortenedUrlEntityMapper::toDomain).toList();
  }

  @Override
  public void save(ShortUrl shortUrlDomain) {
    persist(ShortenedUrlEntityMapper.toEntity(shortUrlDomain));
  }

  public void increaseVisitCount(String id) {
    update("visitCount = visitCount + 1 where id = ?1", UUID.fromString(id));
  }
}
