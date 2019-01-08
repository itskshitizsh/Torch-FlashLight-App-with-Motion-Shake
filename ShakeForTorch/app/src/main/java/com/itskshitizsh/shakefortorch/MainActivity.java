package com.itskshitizsh.shakefortorch;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean isSwitchedOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startService(new Intent(MainActivity.this,ShakeDetectionService.class));
    }

    public void toggle(View view)
    {
        //ImageButton button = findViewById(R.id.imageButton);
    try{
        if(getTitle().equals(getString(R.string.app_title)))
        {
            setTitle(getString(R.string.app_title).concat(" ON"));
            toggleLED("on");
        }else
        if(getTitle().equals(getString(R.string.app_title).concat(" ON")))
        {
            setTitle(getString(R.string.app_title).concat(" OFF"));
            toggleLED("off");
        }else
        if(getTitle().equals(getString(R.string.app_title).concat(" OFF")))
        {
            setTitle(getString(R.string.app_title).concat(" ON"));
            toggleLED("on");
        }
    }catch (Exception e)
        {
            return;
        }
        return;
    }

    private void toggleLED(String str) throws CameraAccessException {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
            String cameraId = null;


            if(cameraManager!=null)
            {
                cameraId = cameraManager.getCameraIdList()[0];
            }

            if(cameraManager!=null)
            {
                if(str.equals("on"))
                {
                    cameraManager.setTorchMode(cameraId,true);
                    isSwitchedOn = true;
                }
                else
                    {
                        cameraManager.setTorchMode(cameraId,false);
                        isSwitchedOn = false;
                    }
            }
        }else
            {
                Toast.makeText(this, "Lower API Version then 23!\n  App: Not of Use for You", Toast.LENGTH_SHORT).show();
            }
    }
}
