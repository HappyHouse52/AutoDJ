package com.happyhouse.autodj.api.representations;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

  private int id;
  private String spotifyId;

  public User() {}

  public User(int id, String spotifyId) {
    this.id = id;
    this.spotifyId = spotifyId;
  }

  public String getSpotifyId() {
    return spotifyId;
  }

  public void setSpotifyId(String id) {
    this.spotifyId = spotifyId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * JDBI Mapper
   */
  public static class UserMapper implements ResultSetMapper<User> {
    public User map(int index, ResultSet r, StatementContext ctx) throws SQLException
    {
      return new User(
          r.getInt("id"),
          r.getString("spotifyId")
      );
    }
  }
}

