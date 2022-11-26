package de.fuh.michel.fachpraktikum_wi2022;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityBarcodeScannerBinding;
import de.fuh.michel.fachpraktikum_wi2022.qrcodescanner.QrCodeAnalyzer;

public class BarcodeScannerActivity extends AppCompatActivity {

    private ExecutorService cameraExecutor;
    private ActivityBarcodeScannerBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBarcodeScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        cameraExecutor = Executors.newSingleThreadExecutor();

        BarcodeBoxView barcodeBoxView = new BarcodeBoxView(this);
        addContentView(barcodeBoxView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        checkCameraPermission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkIfCameraPermissionIsGranted();
    }

    private void checkCameraPermission() {
        try {
            String[] requiredPermissions = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, requiredPermissions, 0);
        } catch (IllegalArgumentException e) {
            checkIfCameraPermissionIsGranted();
        }
    }

    private void checkIfCameraPermissionIsGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted: start the preview
            startCamera();
        } else {
            // Permission denied
            MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
            dialogBuilder.setTitle("Permission required");
            dialogBuilder.setMessage("This application needs to access the camera to process barcodes");
            dialogBuilder.setPositiveButton("Ok", (dialog, which) -> {
                checkCameraPermission();
            });

            AlertDialog alertDialog = dialogBuilder.setCancelable(false).create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    private void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                // Preview
                Preview preview = new Preview.Builder()
                        .build();
                preview.setSurfaceProvider(binding.previewView.getSurfaceProvider());

                // Image analyzer
                ImageAnalysis imageAnalyzer = new ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();
                imageAnalyzer.setAnalyzer(cameraExecutor, new QrCodeAnalyzer(this,
                        new BarcodeBoxView(this),
                        (float) binding.previewView.getWidth(),
                        (float) binding.previewView.getHeight()));

                // Select back camera as a default
                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;


                // Unbind use cases before rebinding
                cameraProvider.unbindAll();

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

}
