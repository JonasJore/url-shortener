package no.jore.infrastructure;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import no.jore.core.application.in.ShortUrlDTO;
import no.jore.core.application.mapper.ShortUrlMapper;
import no.jore.core.domain.ShortUrlEntity;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ShortUrlPanacheRepository implements PanacheRepository<ShortUrlEntity> {
  public Optional<ShortUrlDTO> findById(String id) {
    return Optional.ofNullable(
      ShortUrlEntity.<ShortUrlEntity>findById(id)).map(ShortUrlMapper::toDTO);
  }

  public List<ShortUrlDTO> getAll() {
    return listAll().stream()
      .map(ShortUrlMapper::toDTO)
      .toList();
  }
}
