package com.happyhouse.autodj.api;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.happyhouse.autodj.api.resources.AuthResource;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;
import io.dropwizard.Application;
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

        /* Create a request object. */
        final ClientCredentialsGrantRequest request = spotifyApi.clientCredentialsGrant().build();

        /* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
        final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

        /* Add callbacks to handle success and failure */
        Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
            @Override
            public void onSuccess(ClientCredentials clientCredentials) {
                 /* The tokens were retrieved successfully! */
                System.out.println("Successfully retrieved an access token! " + clientCredentials.getAccessToken());
                System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");

                 /* Set access token on the Api object so that it's used going forward */
                spotifyApi.setAccessToken(clientCredentials.getAccessToken());
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.err.println(throwable.toString());
            }
        });

        environment.jersey().register(new AuthResource(spotifyApi));
    }
}
