package com.happyhouse.autodj.api.guice;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.happyhouse.autodj.api.AutoDjAPIConfiguration;
import com.happyhouse.autodj.api.db.UserDAO;
import com.happyhouse.autodj.api.resources.AuthResource;
import com.happyhouse.autodj.api.services.UserService;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;
import com.wrapper.spotify.Api;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class ApplicationModule extends DropwizardAwareModule<AutoDjAPIConfiguration> {

  @Override
  public void configure(Binder binder) {
    binder.bind(AuthResource.class);
  }

  @Provides
  Api provideSpotifyApi() {
    return Api.builder()
        .clientId(getConfiguration().getSpotifyClientId())
        .clientSecret(getConfiguration().getSpotifyClientSecret())
        .redirectURI(getConfiguration().getSpotifyRedirectURI())
        .build();
  }

  @Provides
  UserService provideUserService() {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(getEnvironment(), getConfiguration().getDataSourceFactory(), "mysql");
    UserDAO dao = jdbi.onDemand(UserDAO.class);
    dao.createUserTable();
    return new UserService(dao);
  }
}
