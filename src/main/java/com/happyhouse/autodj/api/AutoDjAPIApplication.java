package com.happyhouse.autodj.api;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.happyhouse.autodj.api.resources.AuthResource;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.exceptions.WebApiException;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.IOException;

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
        // TODO: application initialization
    }

    @Override
    public void run(final AutoDjAPIConfiguration configuration,
                    final Environment environment) {
        Api spotifyApi = Api.builder()
            .clientId(configuration.getSpotifyClientId())
            .clientSecret(configuration.getSpotifyClientSecret())
            .redirectURI(configuration.getSpotifyRedirectURI())
            .build();

//        final ClientCredentialsGrantRequest request = spotifyApi.clientCredentialsGrant().build();
//        try {
//            ClientCredentials creds = request.get();
//            spotifyApi.setAccessToken(creds.getAccessToken());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(1);
//        }

        environment.jersey().register(new AuthResource(spotifyApi));
    }
}
