package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int count = 0;

    public void buttonPress(View v){
        ImageView imageButtonOn = (ImageView) findViewById(R.id.imageButton);
        ImageView imageMainOff = (ImageView) findViewById(R.id.imageMain);
        ImageView imageButtonOff = (ImageView) findViewById(R.id.imageButton2);
        ImageView imageMainOn = (ImageView) findViewById(R.id.imageMain2);

        if (count % 2 == 1){
//            flashlight is off so on button and off symbol
            imageMainOff.animate().alpha(1f).setDuration(500);
            imageMainOn.animate().alpha(0f).setDuration(500);
            imageButtonOn.animate().alpha(1f).setDuration(200);
            imageButtonOff.animate().alpha(0f).setDuration(200);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraId = null;
                try {
                    cameraId = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraId, false);   //Turn OFF
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        else{
//            flashlight is on so off button and on symbol
            imageMainOff.animate().alpha(0f).setDuration(500);
            imageMainOn.animate().alpha(1f).setDuration(500);
            imageButtonOn.animate().alpha(0f).setDuration(200);
            imageButtonOff.animate().alpha(1f).setDuration(200);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraId = null;
                try {
                    cameraId = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraId, true);   //Turn ON
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        ++count;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}