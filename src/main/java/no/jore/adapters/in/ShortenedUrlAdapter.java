package no.jore.adapters.in;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.jore.core.application.in.RevealedUrlDTO;
import no.jore.core.application.in.ShortUrlDTO;
import no.jore.core.application.mapper.ShortUrlDTOMapper;
import no.jore.core.application.service.ShortenedUrlService;
import no.jore.core.domain.ShortUrl;
import no.jore.ports.in.ShortenedUrlPort;

import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.logging.Logger;


@Path("/shortened-url")
public class ShortenedUrlAdapter implements ShortenedUrlPort {
  private final ShortenedUrlService shortenedUrlService;
  private final static Logger Log = Logger.getLogger("ShortenedUrlResource");

  @Inject
  public ShortenedUrlAdapter(ShortenedUrlService shortenedUrlService) {
    this.shortenedUrlService = shortenedUrlService;
  }


  @Path("/add")
  @POST
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addShortenedUrl(ShortUrlDTO shortUrlDTO) {
    ShortUrl domain = ShortUrlDTOMapper.toDomain(shortUrlDTO, UUID.randomUUID());
    shortenedUrlService.shortenUrl(domain);
    return Response.ok().build();
  }

  @Path("/reveal/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RevealedUrlDTO revealShortenedUrl(@PathParam("id") String id) {
    return shortenedUrlService.findById(id)
      .map(shortUrl -> new RevealedUrlDTO(shortUrl.getUrl()))
      .orElseThrow(() -> new NoSuchElementException("Found no url with id " + id));
  }

  @Path("/getAll")
  @GET
  public Response getAllUrls() {
    return Response.ok(shortenedUrlService.getAll()).build();
  }

}
