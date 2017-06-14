package com.happyhouse.autodj.api.resources;

import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.models.Album;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

  private final Api spotifyApi;

  public AuthResource(Api spotifyApi) {
    this.spotifyApi = spotifyApi;
  }

  @GET
  public String test() {
    AlbumRequest request = this.spotifyApi.getAlbum("7e0ij2fpWaxOEHv5fUYZjd").build();
    try {
      Album album = request.get();
      return album.getName();
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
