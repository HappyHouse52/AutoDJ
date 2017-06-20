package com.happyhouse.autodj.api.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authorization {

  private String authorizeURL;
  private String accessToken;
  private String refreshToken;

  public Authorization() {}

  public Authorization(String authorizeURL) {
    this.authorizeURL = authorizeURL;
  }

  public Authorization(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  @JsonProperty
  public String getAuthorizeURL() {
    return authorizeURL;
  }

  public void setAuthorizeURL(String authorizeURL) {
    this.authorizeURL = authorizeURL;
  }

  @JsonProperty
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  @JsonProperty
  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}
