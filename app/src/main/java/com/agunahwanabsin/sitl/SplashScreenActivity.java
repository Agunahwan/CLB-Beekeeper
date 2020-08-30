package com.agunahwanabsin.sitl;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.agunahwanabsin.sitl.helper.PermissionHelper;
import com.agunahwanabsin.sitl.ui.login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    PermissionHelper permissionHelper;

    TextView tvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //menghilangkan ActionBar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        // Declare object view
        tvSplash = (TextView) findViewById(R.id.tvSplash);

        // Handling permission
        permissionHelper = new PermissionHelper(getApplicationContext(), this);
        permissionHelper.checkAccessFineLocationPermission();
        permissionHelper.checkReadExternalStoragePermission();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, 3000L); //3000 L = 3 detik
    }
}