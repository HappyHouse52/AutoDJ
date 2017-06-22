package com.happyhouse.autodj.api.middleware;

import com.wrapper.spotify.models.User;

import java.security.Principal;

public class SpotifyUser extends User implements Principal {

  public SpotifyUser() {}

  public SpotifyUser(User u) {
    this.setCountry(u.getCountry());
    this.setDisplayName(u.getDisplayName());
    this.setEmail(u.getEmail());
    this.setExternalUrls(u.getExternalUrls());
    this.setFollowers(u.getFollowers());
    this.setHref(u.getHref());
    this.setId(u.getId());
    this.setImages(u.getImages());
    this.setProduct(u.getProduct());
    this.setType(u.getType());
    this.setUri(u.getUri());
  }

  @Override
  public String getName() {
    return this.getDisplayName();
  }
}
