package com.happyhouse.autodj.api.resources;

import com.google.inject.Inject;
import com.happyhouse.autodj.api.middleware.SpotifyUser;
import com.happyhouse.autodj.api.services.UserService;
import com.wrapper.spotify.Api;
import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  private final Api spotifyApi;
  private final UserService userService;

  @Inject
  public UserResource(Api spotifyApi, UserService userService) {
    this.spotifyApi = spotifyApi;
    this.userService = userService;
  }

  @GET
  public String getUser(@Auth SpotifyUser user) {
    return "I'm in " + user.getName();
  }
}
