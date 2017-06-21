package com.happyhouse.autodj.api.middleware;

import javax.security.auth.Subject;
import java.security.Principal;

public class SpotifyUser implements Principal {

  private String id;


  @Override
  public String getName() {
    return null;
  }

  @Override
  public boolean implies(Subject subject) {
    return false;
  }
}
