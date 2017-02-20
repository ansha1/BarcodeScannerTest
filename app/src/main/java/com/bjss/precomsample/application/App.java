package com.bjss.precomsample.application;

import android.app.Application;
import com.bjss.precomsample.di.Injector;
import se.precom.core.AbstractPreComApplication;
import se.precom.core.ContextDaggerModule;
import se.precom.login.ui.AbstractLoginUIApplication;
import se.precom.login.ui.LoginActivityComponentProvider;

public class App extends AbstractLoginUIApplication<AppComponent>
    implements LoginActivityComponentProvider {

  @Override
  public void onCreate() {
    super.onCreate();
  }

  @Override
  public AppComponent createAppComponent() {
    return DaggerAppComponent.builder().contextDaggerModule(new ContextDaggerModule(this)).build();
  }

  @Override
  public Object getSystemService(String name) {
    return Injector.matches(name) ? getAppComponent() : super.getSystemService(name);
  }
}
