package com.happyhouse.autodj.api.middleware;

import com.happyhouse.autodj.api.representations.SpotifyCredentials;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.models.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Optional;

public class SpotifyAuthenticator implements Authenticator<SpotifyCredentials, SpotifyUser> {

  private Api spotifyApi;

  public SpotifyAuthenticator(Api spotifyApi) {
    this.spotifyApi = spotifyApi;
  }

  @Override
  public Optional<SpotifyUser> authenticate(SpotifyCredentials credentials) throws AuthenticationException {

    SpotifyUser user = null;

    this.spotifyApi.setAccessToken(credentials.getAccessToken());
    this.spotifyApi.setRefreshToken(credentials.getRefreshToken());

    try {
      User apiUser = this.spotifyApi.getMe().build().get();
      user = new SpotifyUser(apiUser);
    } catch (IOException | WebApiException e) {
      throw new WebApplicationException("There was a problem authenticating with Spotify. Please try again later.", Response.Status.INTERNAL_SERVER_ERROR);
    }

    return Optional.ofNullable(user);
  }
}
