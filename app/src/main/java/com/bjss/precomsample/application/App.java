package com.bjss.precomsample.application;

import com.bjss.precomsample.di.Injector;
import se.precom.core.ContextDaggerModule;
import se.precom.login.ui.AbstractLoginUIApplication;
import se.precom.login.ui.LoginActivityComponentProvider;

public class App extends AbstractLoginUIApplication<AppComponent>
    implements LoginActivityComponentProvider {
  private AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    component = createAppComponent();
  }

  @Override
  public AppComponent createAppComponent() {
    return DaggerAppComponent.builder()
        .contextDaggerModule(new ContextDaggerModule(this))
        .build();
  }

  @Override
  public Object getSystemService(String name) {
    return Injector.matches(name) ? component : super.getSystemService(name);
  }
}
