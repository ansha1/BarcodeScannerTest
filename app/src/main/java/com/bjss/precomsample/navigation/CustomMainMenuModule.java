package com.bjss.precomsample.navigation;

import com.bjss.precomsample.main.view.MainActivity;
import javax.inject.Inject;
import se.precom.core.ModuleBase;
import se.precom.core.StartAfter;
import se.precom.ui.DefaultUIModule;
import se.precom.ui.MainMenu;

@StartAfter(DefaultUIModule.class)
public class CustomMainMenuModule extends ModuleBase {
  private final MainMenu mainMenu;

  @Inject
  public CustomMainMenuModule(MainMenu mainMenu) {
    this.mainMenu = mainMenu;
  }

  @Override
  protected void onStart() {
    mainMenu.setActivity(MainActivity.class);
  }

  @Override protected void onStop() {

  }
}
