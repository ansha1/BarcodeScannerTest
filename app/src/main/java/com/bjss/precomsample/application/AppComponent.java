package com.bjss.precomsample.application;

import com.bjss.precomsample.di.ApplicationModule;
import com.bjss.precomsample.main.di.MainComponent;
import dagger.Component;
import javax.inject.Singleton;
import se.precom.barcodescanner.BarcodeScannerDaggerModule;
import se.precom.businessrulesengine.BusinessRuleEngineDaggerModule;
import se.precom.core.directory.mapping.DefaultDirectoryMappingsDaggerModule;
import se.precom.login.ui.LoginActivityComponentProvider;
import se.precom.login.ui.LoginUIAppComponent;
import se.precom.login.ui.LoginUIDaggerModule;
import se.precom.masterdatasync.MasterDataSyncDaggerModule;
import se.precom.positioning.PositioningDaggerModule;
import se.precom.ui.PreComUIDaggerModule;

/**
 * <p>Dagger will generate an implementation of this class called {@link DaggerAppComponent}.</p>
 *
 * <p>Singleton annotation tells dagger that it should only exist one instance of this class. It
 * also helps dagger figuring out how all the dagger modules fits together.</p>
 *
 * <p>The Component annotation is what Dagger uses to find all component classes. Add dagger
 * modules from PreCom dependencies and custom ones in the modules attribute.</p>
 */
@Singleton
@Component(modules = {
    PreComUIDaggerModule.class,
    DefaultDirectoryMappingsDaggerModule.class,
    LoginUIDaggerModule.class,
    MasterDataSyncDaggerModule.class,
    BarcodeScannerDaggerModule.class,
    BusinessRuleEngineDaggerModule.class,
    BjssPreComModule.class,
    ApplicationModule.class,
    PositioningDaggerModule.class})
public abstract class AppComponent extends LoginUIAppComponent
    implements LoginActivityComponentProvider {


  public abstract MainComponent with();
}
