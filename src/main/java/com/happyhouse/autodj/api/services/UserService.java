package com.happyhouse.autodj.api.services;

import com.happyhouse.autodj.api.db.UserDAO;
import com.happyhouse.autodj.api.representations.User;

public class UserService {

  private final UserDAO userDAO;

  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public User createUser(String id) {
    this.userDAO.insert(id);
    return this.userDAO.findById(id);
  }

  public User findById(String id) {
    return this.userDAO.findById(id);
  }
}
