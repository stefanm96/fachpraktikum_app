package de.fuh.michel.fachpraktikum_wi2022;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.fuh.michel.fachpraktikum_wi2022.databinding.ActivityQrCodeScannerBinding;
import de.fuh.michel.fachpraktikum_wi2022.domain.qrcodescanner.QrCodeAnalyzer;
import de.fuh.michel.fachpraktikum_wi2022.view.ProcessFlowViewModel;

public class QrCodeScannerActivity extends AppCompatActivity {

    private ExecutorService cameraExecutor;
    private ActivityQrCodeScannerBinding binding;
    private ProcessFlowViewModel processFlowViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrCodeScannerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        processFlowViewModel = ((GmafApplication) getApplication()).getProcessFlowViewModel();

        cameraExecutor = Executors.newSingleThreadExecutor();

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

    public void checkCameraPermission() {
        try {
            String[] requiredPermissions = {Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, requiredPermissions, 0);
        } catch (IllegalArgumentException e) {
            checkIfCameraPermissionIsGranted();
        }
    }

    void checkIfCameraPermissionIsGranted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // Permission granted: start the preview
            startCamera();
        } else {
            // Permission denied
            MaterialAlertDialogBuilder dialogBuilder = new MaterialAlertDialogBuilder(this);
            dialogBuilder.setTitle("Permission required");
            dialogBuilder.setMessage("This application needs to access the camera to process barcodes");
            dialogBuilder.setPositiveButton("Ok", (dialog, which) -> checkCameraPermission());

            AlertDialog alertDialog = dialogBuilder.setCancelable(false).create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    public void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                Preview preview = new Preview.Builder().build();
                preview.setSurfaceProvider(binding.previewView.getSurfaceProvider());

                ImageAnalysis imageAnalyzer = new ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();
                imageAnalyzer.setAnalyzer(cameraExecutor, new QrCodeAnalyzer(this,
                        stringContent -> processFlowViewModel.importDefinition(stringContent)));

                CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
                cameraProvider.unbindAll();
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageAnalyzer);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }

}
