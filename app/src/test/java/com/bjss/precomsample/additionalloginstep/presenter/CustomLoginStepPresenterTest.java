package com.bjss.precomsample.additionalloginstep.presenter;

import com.bjss.precomsample.R;
import com.bjss.precomsample.additionalloginstep.view.AdditionalLoginStepView;
import com.bjss.precomsample.additionalloginstep.view.Data;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import se.precom.login.ILoginController;

import static com.bjss.precomsample.util.Constant.GET_MAIL_CENTRE;
import static com.bjss.precomsample.util.Constant.GET_SHIFT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomLoginStepPresenterTest {
  private static final String MESSAGE = "message";
  @Mock ILoginController loginController;
  @Mock AdditionalLoginStepView view;
  @Mock ResourcesProxy resourcesProxy;
  @Mock DataParser dataParser;
  @Mock Validator validator;
  @Mock Shift shift;
  @Mock MailCentre mailCentre;
  @Mock Localizer localizer;
  @InjectMocks CustomLoginStepPresenter presenter;

  /** Setup. */
  @Before
  public void setUp() {
    presenter.bindView(view);
    when(shift.getDescription()).thenReturn(MESSAGE);
    when(mailCentre.getDescription()).thenReturn(MESSAGE);
    when(dataParser.getShift(isA(Data.class))).thenReturn(shift);
    when(dataParser.getMailCentre(isA(Data.class))).thenReturn(mailCentre);
  }

  @Test
  public void when_placeIsClicked_then_goToSelectMailCentre() {
    // WHEN
    presenter.onMailCentreClicked();
    // THEN
    verify(view).goToSelectMailCentre();
  }

  @Test
  public void when_shiftIsClicked_then_goToSelectShift() {
    // WHEN
    presenter.onShiftClicked();
    // THEN
    verify(view).goToSelectShift();
  }

  @Test
  public void when_errorGettingMailCentre_then_userIsAlerted() {
    // GIVEN
    when(resourcesProxy.getString(R.string.error_getting_mail_centre)).thenReturn(MESSAGE);
    // WHEN
    presenter.onResultReturned(GET_MAIL_CENTRE, 0, new Data(null));
    // THEN
    verify(view).onError(MESSAGE);
  }

  @Test
  public void when_errorGettingShift_then_userIsAlerted() {
    // GIVEN
    when(resourcesProxy.getString(R.string.error_getting_shift)).thenReturn(MESSAGE);
    // WHEN
    presenter.onResultReturned(GET_SHIFT, 0, new Data(null));
    // THEN
    verify(view).onError(MESSAGE);
  }

  @Test
  public void when_GettingShiftSuccess_then_viewIsSet() {
    // GIVEN
    when(validator.isShiftValid(isA(Shift.class))).thenReturn(true);
    // WHEN
    presenter.onResultReturned(GET_SHIFT, -1, new Data(null));
    // THEN
    verify(view).setShift(MESSAGE);
  }

  @Test
  public void when_GettingMailCentreSuccess_then_viewIsSet() {
    // GIVEN
    when(validator.isMailCentreValid(isA(MailCentre.class))).thenReturn(true);
    // WHEN
    presenter.onResultReturned(GET_MAIL_CENTRE, -1, new Data(null));
    // THEN
    verify(view).setMailCentre(MESSAGE);
  }

  @Test
  public void when_errorParsingShift_then_userIsInformed() {
    // GIVEN
    when(validator.isShiftValid(isA(Shift.class))).thenReturn(false);
    when(validator.getErrorMessage(isA(Shift.class))).thenReturn(MESSAGE);
    // WHEN
    presenter.onResultReturned(GET_SHIFT, -1, new Data(null));
    // THEN
    verify(view).onError(MESSAGE);
  }

  @Test
  public void when_errorParsingMailCentre_then_userIsInformed() {
    // GIVEN
    when(validator.isMailCentreValid(isA(MailCentre.class))).thenReturn(false);
    when(validator.getErrorMessage(isA(MailCentre.class))).thenReturn(MESSAGE);
    // WHEN
    presenter.onResultReturned(GET_MAIL_CENTRE, -1, new Data(null));
    // THEN
    verify(view).onError(MESSAGE);
  }

  @Test
  public void when_bindView_then_isNotEnabled() {
    // GIVEN
    when(validator.areBothValid(null, null)).thenReturn(false);
    // WHEN
    // THEN
    // two times: constructor and after
    verify(view).setConfirmEnabled(false);
  }

  @Test
  public void when_GettingShiftSuccessAndBothValid_then_isEnabled() {
    // GIVEN
    when(validator.isShiftValid(isA(Shift.class))).thenReturn(true);
    when(validator.areBothValid(any(Shift.class), isNull(MailCentre.class))).thenReturn(true);
    // WHEN
    presenter.onResultReturned(GET_SHIFT, -1, new Data(null));
    // THEN
    verify(view).setConfirmEnabled(true);
  }

  @Test
  public void when_GettingMailCenteSuccessAndBothValid_then_isEnabled() {
    // GIVEN
    when(validator.isMailCentreValid(isA(MailCentre.class))).thenReturn(true);
    when(validator.areBothValid(isNull(Shift.class), isA(MailCentre.class))).thenReturn(true);
    // WHEN
    presenter.onResultReturned(GET_MAIL_CENTRE, -1, new Data(null));
    // THEN
    verify(view).setConfirmEnabled(true);
  }

  @Test
  public void when_clickConfirmAndAllIsGood_then_goToNext() {
    // GIVEN
    when(validator.areBothValid(isNull(Shift.class), isNull(MailCentre.class))).thenReturn(true);
    // WHEN
    presenter.onConfirmClicked();
    // THEN
    verify(loginController).navigateNextLoginStep();
  }

  @Test
  public void when_clickConfirmAndAllIsNotGood_then_userIsAlerted() {
    // GIVEN
    when(validator.areBothValid(isNull(Shift.class), isNull(MailCentre.class))).thenReturn(false);
    when(validator.getErrorMessage(isNull(Shift.class), isNull(MailCentre.class))).thenReturn(
        MESSAGE);
    // WHEN
    presenter.onConfirmClicked();
    // THEN
    verify(view).onError(MESSAGE);
  }

  @Test
  public void when_resumes_then_subscribes() throws Exception {
    // WHEN
    presenter.onResume();
    // THEN
    verify(localizer).subscribe(isA(Localizer.Callback.class));
  }

  @Test
  public void when_pauses_then_unSubscribes() throws Exception {
    // WHEN
    presenter.onPause();
    // THEN
    verify(localizer).unsubscribe();
  }

  @Test
  public void when_centerIsAvailable_then_unsubscribes() throws Exception {
    // WHEN
    presenter.onResultReturned(GET_MAIL_CENTRE, -1, new Data(null));
    // THEN
    verify(localizer).subscribe(isNull(Localizer.Callback.class ));
  }

}