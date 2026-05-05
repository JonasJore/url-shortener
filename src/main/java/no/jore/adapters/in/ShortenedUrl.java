package no.jore.adapters.in;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import no.jore.core.application.in.RevealedUrlDTO;
import no.jore.core.application.in.ShortUrlDTO;

@Path("/shortened-url")
public class ShortenedUrl {
  private ShortUrlDTO shortened;

  @Path("/{id}")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getShortenedUrl(@PathParam("id") String id) {
    return "Shortened" + id;
  }

  @Path("/add")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response addShortenedUrl(ShortUrlDTO shortUrlDTO) {
    this.shortened = shortUrlDTO;
    System.out.println("Shortened");
    System.out.println(this.shortened.getId());
    return Response.ok().build();
  }

  @Path("/reveal/{id}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public RevealedUrlDTO revealShortenedUrl(@PathParam("id") String id) throws Exception {
    System.out.println("id:  " + id);
    if (this.shortened.getId().toString().equals(id)) {
      return new RevealedUrlDTO(this.shortened.getUrl());
    }
    throw new Exception("");
  }

}
