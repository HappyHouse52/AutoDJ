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

  public User createUser(String id) {
    this.userDAO.insert(id);
    return this.userDAO.findById(id);
  }

  public User findById(String id) {
    return this.userDAO.findById(id);
  }
}
