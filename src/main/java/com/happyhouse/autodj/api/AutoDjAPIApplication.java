package com.happyhouse.autodj.api;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.happyhouse.autodj.api.db.UserDAO;
import com.happyhouse.autodj.api.guice.ApplicationModule;
import com.happyhouse.autodj.api.resources.AuthResource;
import com.happyhouse.autodj.api.services.UserService;
import com.hubspot.dropwizard.guicier.GuiceBundle;
import com.wrapper.spotify.Api;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

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
            .modules(new ApplicationModule())
            .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final AutoDjAPIConfiguration configuration,
                    final Environment environment) { }
}
