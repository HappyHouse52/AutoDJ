package com.happyhouse.autodj.api.resources;

import com.google.common.base.Strings;
import com.happyhouse.autodj.api.representations.Authorization;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.AlbumRequest;
import com.wrapper.spotify.models.Album;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

  private final Api spotifyApi;

  public AuthResource(Api spotifyApi) {
    this.spotifyApi = spotifyApi;
  }

  @GET
  public Authorization login(@QueryParam("code") String code,
                             @QueryParam("state") String state) {

    if (!Strings.isNullOrEmpty(code) && !Strings.isNullOrEmpty(state)) {
      return this.loginSuccess(code, state);
    } else {
      return this.getSpotifyAuth();
    }
  }

  private Authorization getSpotifyAuth() {
    final List<String> scopes = Arrays.asList("user-read-private", "user-read-email");
    final String newState = "someExpectedStateString";
    return new Authorization(this.spotifyApi.createAuthorizeURL(scopes, newState));
  }

  private Authorization loginSuccess(String code, String state) {
    return new Authorization(null, code, state);
  }
}
