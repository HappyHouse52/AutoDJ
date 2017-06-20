package com.happyhouse.autodj.api.guice;

import com.google.inject.Binder;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.happyhouse.autodj.api.AutoDjAPIConfiguration;
import com.happyhouse.autodj.api.db.AbstractDAO;
import com.hubspot.dropwizard.guicier.DropwizardAwareModule;
import io.dropwizard.jdbi.DBIFactory;
import org.skife.jdbi.v2.DBI;

public class DaoModule<T extends AbstractDAO> extends DropwizardAwareModule<AutoDjAPIConfiguration> {

  private Class<T> daoType;

  public DaoModule(Class<T> daoType) {
    this.daoType = daoType;
  }

  @Override
  public void configure(Binder binder) {
    binder.bind(daoType).toProvider(() -> {
      final DBIFactory factory = new DBIFactory();
      final DBI jdbi = factory.build(getEnvironment(), getConfiguration().getDataSourceFactory(), "mysql");
      return jdbi.onDemand(DaoModule.this.daoType);
    });
  }
}
