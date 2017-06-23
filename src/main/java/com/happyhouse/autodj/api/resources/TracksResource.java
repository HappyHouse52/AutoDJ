package com.happyhouse.autodj.api.resources;

import com.google.inject.Inject;
import com.happyhouse.autodj.api.middleware.SpotifyUser;
import com.happyhouse.autodj.api.services.UserService;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.Page;
import com.wrapper.spotify.models.Track;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/tracks")
@Produces(MediaType.APPLICATION_JSON)
public class TracksResource {

  private final Api spotifyApi;

  private static final int PAGE_SIZE = 10;

  @Inject
  public TracksResource(Api spotifyApi) {
    this.spotifyApi = spotifyApi;
  }

  @GET
  @Path("{query}")
  public Page<Track> search(@Auth SpotifyUser user, @PathParam("query") String query, @QueryParam("page") int page) {
    Page<Track> result = null;
    this.spotifyApi.setAccessToken(user.getAccessToken());
    try {
      result = this.spotifyApi.searchTracks(query).market("US").offset(PAGE_SIZE * page).build().get();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (WebApiException e) {
      e.printStackTrace();
    }
    return result;
  }
}
