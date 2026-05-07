package no.jore.adapters.in;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.jore.core.application.in.RevealedUrlDTO;
import no.jore.core.application.in.ShortUrlDTO;
import no.jore.core.application.service.ShortenedUrlService;

import java.util.logging.Logger;


@Path("/shortened-url")
public class ShortenedUrlResource {
  private ShortUrlDTO shortened;
  private ShortenedUrlService shortenedUrlService;

  private final static Logger Log = Logger.getLogger("ShortenedUrlResource");

  @Inject
  public ShortenedUrlResource(ShortenedUrlService shortenedUrlService) {
    this.shortenedUrlService = shortenedUrlService;
  }

  @Path("/{id}")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getShortenedUrl(@PathParam("id") String id) {
    return "Shortened" + id;
  }

  @Path("/add")
  @POST
  @Transactional
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addShortenedUrl(ShortUrlDTO shortUrlDTO) {
    this.shortened = shortUrlDTO;
    shortenedUrlService.shortenUrl(shortUrlDTO);
    return Response.ok().build();
  }

  @Path("/reveal/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RevealedUrlDTO revealShortenedUrl(@PathParam("id") String id) throws Exception {
    Log.info("Revealing " + id);
    if (this.shortened.getId().toString().equals(id)) {
      return new RevealedUrlDTO(this.shortened.getUrl());
    }
    throw new Exception("");
  }

  @Path("/getAll")
  @GET
  public Response getAllUrls() {
    return Response.ok(shortenedUrlService.getAll()).build();
  }

}
