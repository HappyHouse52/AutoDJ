package com.happyhouse.autodj.api.middleware;

import javax.security.auth.Subject;
import java.security.Principal;

public class SpotifyUser implements Principal {

  private String id;
  private String name;

  public SpotifyUser(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean implies(Subject subject) {
    return false;
  }
}
