package com.bjss.precomsample.application;

import com.bjss.precomsample.login.CustomLoginModule;
import com.bjss.precomsample.navigation.CustomMainMenuModule;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import se.precom.core.PreComBase;

/**
 * Registers {@link CustomLoginModule} into PreCom.
 */
@Module
public abstract class BjssPreComModule {
  @Binds
  @IntoSet
  public abstract PreComBase bindCustomLoginModule(CustomLoginModule module);

  @Binds
  @IntoSet
  public abstract PreComBase bindCustomMainMenuModule(CustomMainMenuModule module);
}
