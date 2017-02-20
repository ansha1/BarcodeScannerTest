package com.bjss.precomsample.login;

import android.view.View;
import se.precom.login.LoginQualifier;
import se.precom.login.ui.LoginFragment;

@LoginQualifier public class CustomLoginFragment extends LoginFragment {
  @Override protected void initializeControls(View view) {
    super.initializeControls(view);

    // hard code login for development
    setUsername("User");
    setPassword("PreCom4!");
  }
}
