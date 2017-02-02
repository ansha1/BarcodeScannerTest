package com.bjss.precomsample.main.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bjss.precomsample.application.App;
import com.bjss.precomsample.R;
import com.bjss.precomsample.application.AppComponent;
import javax.inject.Inject;
import se.precom.barcodescanner.Barcode;
import se.precom.barcodescanner.BarcodeError;
import se.precom.barcodescanner.IBarcodeScanner;

import static com.bjss.precomsample.di.Injector.INJECTOR_SERVICE;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "SCAN!";
  @Inject protected IBarcodeScanner scanner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    intitDi();
    ButterKnife.bind(MainActivity.this);
    scanner.addBarcodeCaptureListener(new IBarcodeScanner.IBarcodeCaptureListener() {
      @Override
      public boolean onBarcodeCaptured(Barcode barcode) {
        Log.e(TAG, barcode.getBarcodeContent());
        return false;
      }

      @Override
      public void onBarcodeScanFailed(BarcodeError barcodeError, String s) {
        Log.e(TAG, barcodeError.toString());
      }
    });
  }

  private void intitDi() {
    (((AppComponent) ((App) getApplication()).getSystemService(INJECTOR_SERVICE)).with()).inject(
        MainActivity.this);
  }

  @OnClick(R.id.scan)
  public void scan() {
    scanner.scanBarcode(MainActivity.this);
  }
}
