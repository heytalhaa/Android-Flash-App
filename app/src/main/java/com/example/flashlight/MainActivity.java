package com.example.flashlight;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
        Button flashBtn;
        private  String cameraId;
        private CameraManager cameraManager;
        private boolean isBoolean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashBtn = findViewById(R.id.flashlightToggle);

        cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            throw new RuntimeException(e);
        }

        flashBtn.setOnClickListener(view -> {
            if (isBoolean){
                    flashLightOn();
                flashBtn.setText("Turn OFF");
            } else {
                flashLightOff();
                flashBtn.setText("Turn ON");
            }
            isBoolean =! isBoolean;
        });
    }

    private void flashLightOff() {
        try {
            cameraManager.setTorchMode(cameraId, false);
        } catch (CameraAccessException e){
            throw new RuntimeException(e);
        }
    }

    private void flashLightOn() {
        try {
            cameraManager.setTorchMode(cameraId, true);
        } catch (CameraAccessException e){
            throw new RuntimeException(e);
        }
    }
}