package com.happyhouse.autodj.api;

import com.happyhouse.autodj.api.guice.ApplicationModule;
import com.hubspot.dropwizard.guicier.GuiceBundle;
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
        GuiceBundle<AutoDjAPIConfiguration> guiceBundle = GuiceBundle.defaultBuilder(AutoDjAPIConfiguration.class)
            .enableGuiceEnforcer(false)
            .modules(new ApplicationModule())
            .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final AutoDjAPIConfiguration configuration,
                    final Environment environment) { }
}
