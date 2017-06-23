package com.happyhouse.autodj.api.guice;

import com.google.inject.Binder;
import com.google.inject.Provides;
import com.happyhouse.autodj.api.AutoDjAPIConfiguration;
import com.happyhouse.autodj.api.resources.AuthResource;
import com.happyhouse.autodj.api.resources.TracksResource;
import com.happyhouse.autodj.api.resources.UserResource;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;
import com.wrapper.spotify.Api;

public class ApplicationModule extends DropwizardAwareModule<AutoDjAPIConfiguration> {

  @Override
  public void configure(Binder binder) {
    binder.bind(AuthResource.class);
    binder.bind(UserResource.class);
    binder.bind(TracksResource.class);
  }

  @Provides
  Api provideSpotifyApi() {
    return Api.builder()
        .clientId(getConfiguration().getSpotifyClientId())
        .clientSecret(getConfiguration().getSpotifyClientSecret())
        .redirectURI(getConfiguration().getSpotifyRedirectURI())
        .build();
  }
}
