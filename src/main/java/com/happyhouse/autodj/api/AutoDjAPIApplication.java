package com.happyhouse.autodj.api;

import com.happyhouse.autodj.api.db.UserDAO;
import com.happyhouse.autodj.api.guice.ApplicationModule;
import com.happyhouse.autodj.api.guice.DaoModule;
import com.happyhouse.autodj.api.middleware.SpotifyAuthFilter;
import com.happyhouse.autodj.api.middleware.SpotifyAuthenticator;
import com.happyhouse.autodj.api.middleware.SpotifyUser;
import com.hubspot.dropwizard.guicier.GuiceBundle;
import com.wrapper.spotify.Api;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AutoDjAPIApplication extends Application<AutoDjAPIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new AutoDjAPIApplication().run(args);
    }

    @Override
    public String getName() {
        return "AutoDjAPI";
    }

    @Override
    public void initialize(final Bootstrap<AutoDjAPIConfiguration> bootstrap) {
        GuiceBundle<AutoDjAPIConfiguration> guiceBundle = GuiceBundle.defaultBuilder(AutoDjAPIConfiguration.class)
            .enableGuiceEnforcer(false)
            .modules(
                new ApplicationModule(),
                new DaoModule<>(UserDAO.class)
                )
            .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final AutoDjAPIConfiguration configuration,
                    final Environment environment) {
        this.configureAuth(configuration, environment);
    }

    private void configureAuth(final AutoDjAPIConfiguration configuration,
                               final Environment environment) {
        Api spotifyApi = Api.builder()
            .clientId(configuration.getSpotifyClientId())
            .clientSecret(configuration.getSpotifyClientSecret())
            .redirectURI(configuration.getSpotifyRedirectURI())
            .build();

        environment.jersey().register(new AuthDynamicFeature(new SpotifyAuthFilter(
            new SpotifyAuthenticator(spotifyApi),
            "BASIC-AUTH-REALM"
        )));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(SpotifyUser.class));
    }
}
