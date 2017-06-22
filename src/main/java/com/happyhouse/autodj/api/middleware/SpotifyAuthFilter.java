package com.happyhouse.autodj.api.middleware;

import com.google.common.base.Strings;
import com.happyhouse.autodj.api.representations.SpotifyCredentials;
import io.dropwizard.auth.AuthFilter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;

@Priority(Priorities.AUTHENTICATION)
public class SpotifyAuthFilter extends AuthFilter<SpotifyCredentials, SpotifyUser> {

  public static final String REFRESH_TOKEN_HEADER = "X-spotify-refresh-token";

  public SpotifyAuthFilter(SpotifyAuthenticator authenticator, String realm) {
    this.authenticator = authenticator;
    this.realm = realm;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    SpotifyCredentials credentials = getCredentials(requestContext);

    if (!authenticate(requestContext, credentials, SecurityContext.BASIC_AUTH)) {
      throw new WebApplicationException("Credentials not valid", Response.Status.UNAUTHORIZED);
    }
  }

  private SpotifyCredentials getCredentials(ContainerRequestContext requestContext) {
    SpotifyCredentials credentials = new SpotifyCredentials();

    String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
    String refreshToken = requestContext.getHeaderString(REFRESH_TOKEN_HEADER);

    if (!Strings.isNullOrEmpty(token) && !Strings.isNullOrEmpty(refreshToken)) {
      credentials.setAccessToken(token);
      credentials.setRefreshToken(refreshToken);
    } else {
      throw new WebApplicationException("Unable to parse credentials", Response.Status.UNAUTHORIZED);
    }

    return credentials;
  }
}
