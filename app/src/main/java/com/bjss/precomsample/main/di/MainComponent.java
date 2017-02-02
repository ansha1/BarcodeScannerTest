package com.bjss.precomsample.main.di;

import com.bjss.precomsample.main.view.MainActivity;
import dagger.Subcomponent;

@Subcomponent(modules = MainModule.class)
public interface MainComponent {

  void inject(MainActivity  mainActivity);

}
