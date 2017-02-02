package com.bjss.precomsample.login;
import javax.inject.Inject;
import se.precom.core.ModuleBase;
import se.precom.core.StartAfter;
import se.precom.login.ILoginController;
import se.precom.login.ui.ILoginUI;
import se.precom.login.ui.LoginUIModule;

/**
 * The purpose of this module is to show how to set a default login step for the LoginModule.
 * Look at the unit test MyModuleTest for an example of how you can test this class.
 */
@StartAfter(LoginUIModule.class)
public class CustomLoginModule extends ModuleBase {
  private ILoginUI login;
  private ILoginController loginController;

  // Inject ILoginUI to be able to set default login step. The ILoginUI dependency is
  // provided through the LoginUIDaggerModule class specified in the AppComponent
  @Inject
  public CustomLoginModule(ILoginUI login, ILoginController loginController) {
    this.login = login;
    this.loginController = loginController;
  }

  @Override
  protected void onStart() {
    // This adds the login step to login module
    login.setDefaultLoginStep(new CustomLoginFragment());
  }

  @Override
  protected void onStop() {

  }
}
