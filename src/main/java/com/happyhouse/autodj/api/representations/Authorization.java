package com.happyhouse.autodj.api.representations;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kmoses on 6/19/17.
 */
public class Authorization {

  private String authorizeURL;
  private String code;
  private String state;

  public Authorization() {}

  public Authorization(String authorizeURL) {
    this.authorizeURL = authorizeURL;
  }

  public Authorization(String authorizeURL, String code, String state) {
    this.authorizeURL = authorizeURL;
    this.code = code;
    this.state = state;
  }

  @JsonProperty
  public String getAuthorizeURL() {
    return authorizeURL;
  }

  public void setAuthorizeURL(String authorizeURL) {
    this.authorizeURL = authorizeURL;
  }

  @JsonProperty
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @JsonProperty
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
