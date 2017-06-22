package com.happyhouse.autodj.api.middleware;

import com.happyhouse.autodj.api.representations.SpotifyCredentials;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;

import java.util.Optional;

public class SpotifyAuthenticator implements Authenticator<SpotifyCredentials, SpotifyUser> {

  @Override
  public Optional<SpotifyUser> authenticate(SpotifyCredentials credentials) throws AuthenticationException {

    return Optional.ofNullable(new SpotifyUser("43424234", "Kevin"));
  }
}
