package com.example.jonathanmoreno.flashlightapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.floatingActionButton)
    FloatingActionButton FAB;

    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayoutBackground;

    private CameraManager cameraManager;
    private String cameraId;
    private Boolean isFlashLightOn;
    private Boolean doesPhoneHaveFlash;

    int blackBackground;
    int whiteBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        isFlashLightOn = false;

        blackBackground = ContextCompat.getColor(getApplicationContext(),R.color.offLightColor);
        whiteBackground =   ContextCompat.getColor(getApplicationContext(),R.color.onLightColor);

        doesPhoneHaveFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        checkIfPhoneHasFlash();
    }



    //Button pressed
    @OnClick(R.id.floatingActionButton)
    public void buttonPressed(){

        try {
            if (isFlashLightOn) {
                checkIfPhoneHasFlash();
                flashLightOff();
                isFlashLightOn = false;
            } else {
                flashLightOn();
                isFlashLightOn = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


   //Alert dialog to see if flash is available on your phone
    public void checkIfPhoneHasFlash(){
        if(!doesPhoneHaveFlash){
            AlertDialog alert = new AlertDialog.Builder(MainActivity.this)
                    .create();
            alert.setTitle("Warning!");
            alert.setMessage("Your device doesn't support flash!");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // closing the application
                    finish();
                    System.exit(0);
                }
            });
            alert.show();
            return;
        }
    }


    public void flashLightOn(){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager.setTorchMode(cameraId, true);
                relativeLayoutBackground.setBackgroundColor(whiteBackground);
                FAB.setImageResource(R.drawable.ic_brightness_high_black_24dp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   public void flashLightOff(){
       try {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               cameraManager.setTorchMode(cameraId, false);
               relativeLayoutBackground.setBackgroundColor(blackBackground);
               FAB.setImageResource(R.drawable.ic_brightness_low_black_24dp);

           }

       } catch (Exception e) {
           e.printStackTrace();
       }

   }


    @Override
    protected void onStop() {
        super.onStop();
        if(isFlashLightOn){
            flashLightOff();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(isFlashLightOn){
            flashLightOff();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( isFlashLightOn ){
            flashLightOn();
        }
    }




}
