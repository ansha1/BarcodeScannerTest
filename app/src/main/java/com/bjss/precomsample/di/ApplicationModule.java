package com.bjss.precomsample.di;

import android.content.Context;
import android.support.annotation.NonNull;
import dagger.Module;

@Module
public class ApplicationModule {
  private final Context context;

  public ApplicationModule(@NonNull Context context) {
    this.context = context.getApplicationContext();
  }
}
