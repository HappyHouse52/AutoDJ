package com.happyhouse.autodj.api.db;

import com.happyhouse.autodj.api.representations.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(User.UserMapper.class)
public interface UserDAO {

  @SqlUpdate("create table User (id varchar(300) primary key)")
  void createUserTable();

  @SqlUpdate("insert into User (id) values (:id)")
  void insert(@Bind("id") String id);

  @SqlQuery("select * from User where id = :id")
  User findById(@Bind("id") String id);

  /**
   * close with no args is used to close the connection
   */
  void close();
}
