package com.happyhouse.autodj.api.services;

import com.google.inject.Inject;
import com.happyhouse.autodj.api.db.UserDAO;
import com.happyhouse.autodj.api.representations.User;

public class UserService {

  private final UserDAO userDAO;

  @Inject
  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
    this.userDAO.createUserTable();
  }

  public User createUser(String spotifyId) {
    this.userDAO.insert(spotifyId);
    return this.userDAO.findBySpotifyId(spotifyId);
  }

  public User findById(String spotifyId) {
    return this.userDAO.findBySpotifyId(spotifyId);
  }
}
