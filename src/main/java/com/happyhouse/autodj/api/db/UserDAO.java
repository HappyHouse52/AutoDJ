package com.happyhouse.autodj.api.db;

import com.happyhouse.autodj.api.representations.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(User.UserMapper.class)
public interface UserDAO extends AbstractDAO {

  @SqlUpdate("create table if not exists User (" +
      "id int AUTO_INCREMENT PRIMARY KEY, " +
      "spotifyId int NOT NULL UNIQUE)")
  void createUserTable();

  @SqlUpdate("insert into User (spotifyId) values (:spotifyId)")
  void insert(@Bind("spotifyId") String spotifyId);

  @SqlQuery("select * from User where spotifyId = :spotifyId")
  User findBySpotifyId(@Bind("spotifyId") String spotifyId);

  /**
   * close with no args is used to close the connection
   */
  void close();
}
