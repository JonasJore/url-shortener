package no.jore.ports.in;

import jakarta.ws.rs.core.Response;
import no.jore.core.application.in.RevealedUrlDTO;
import no.jore.core.application.in.ShortUrlDTO;

import java.util.NoSuchElementException;

public interface ShortenedUrlPort {
  Response addShortenedUrl(ShortUrlDTO shortUrlDTO);

  RevealedUrlDTO revealShortenedUrl(String id) throws NoSuchElementException;

  Response getAllUrls();

  Response redirect(String id);
}
