package de.fuh.michel.fachpraktikum_wi2022.domain.qrcodescanner;

import android.annotation.SuppressLint;
import android.media.Image;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;
import java.util.function.Consumer;

import de.fuh.michel.fachpraktikum_wi2022.BarcodeScannerActivity;

public class QrCodeAnalyzer implements ImageAnalysis.Analyzer {

    private final AppCompatActivity context;
    private final Consumer<String> qrCodeListener;

    public QrCodeAnalyzer(BarcodeScannerActivity context, Consumer<String> qrCodeListener) {
        this.context = context;
        this.qrCodeListener = qrCodeListener;
    }

    @Override
    @SuppressLint("UnsafeOptInUsageError")
    public void analyze(@NonNull ImageProxy image) {
        Image img = image.getImage();

        if (img == null) {
            image.close();
            return;
        }

        InputImage inputImage = InputImage.fromMediaImage(img, image.getImageInfo().getRotationDegrees());

        BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
                .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                .build();

        BarcodeScanner scanner = BarcodeScanning.getClient(options);

        scanner.process(inputImage).addOnSuccessListener(this::scanBarcodes);

        image.close();
    }

    private void scanBarcodes(List<Barcode> barcodes) {
        if (!barcodes.isEmpty()) {
            for (Barcode barcode : barcodes) {
                if (barcode.getFormat() == Barcode.FORMAT_QR_CODE) {
                    Toast.makeText(context,
                                    "Value: " + barcode.getRawValue(),
                                    Toast.LENGTH_SHORT)
                            .show();
                    qrCodeListener.accept(barcode.getRawValue());
                    context.finish();
                    return;
                }
            }
        }
    }
}