package com.bjss.precomsample.di;

import android.content.Context;
import android.support.annotation.Nullable;
import com.bjss.precomsample.application.AppComponent;

public final class Injector {
  public static final String INJECTOR_SERVICE = "com.bjss.precomsample.injector";

  private Injector() {
    throw new UnsupportedOperationException();
  }

  @SuppressWarnings({ "ResourceType", "WrongConstant" })
  public static AppComponent obtain(Context context) {
    final Context applicationContext = context.getApplicationContext();
    return (AppComponent) applicationContext.getSystemService(INJECTOR_SERVICE);
  }

  public static boolean matches(@Nullable String name) {
    return INJECTOR_SERVICE.equals(name);
  }
}
