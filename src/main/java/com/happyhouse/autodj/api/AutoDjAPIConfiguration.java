package com.happyhouse.autodj.api;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

public class AutoDjAPIConfiguration extends Configuration {

  @NotEmpty
  private String spotifyClientId;

  @NotEmpty
  private String spotifyClientSecret;

  @NotEmpty
  private String spotifyRedirectURI;

  @JsonProperty
  public String getSpotifyClientId() {
    return spotifyClientId;
  }

  @JsonProperty
  public void setSpotifyClientId(String spotifyClientId) {
    this.spotifyClientId = spotifyClientId;
  }

  @JsonProperty
  public String getSpotifyClientSecret() {
    return spotifyClientSecret;
  }

  @JsonProperty
  public void setSpotifyClientSecret(String spotifyClientSecret) {
    this.spotifyClientSecret = spotifyClientSecret;
  }

  @JsonProperty
  public String getSpotifyRedirectURI() {
    return spotifyRedirectURI;
  }

  @JsonProperty
  public void setSpotifyRedirectURI(String spotifyRedirectURI) {
    this.spotifyRedirectURI = spotifyRedirectURI;
  }
}
