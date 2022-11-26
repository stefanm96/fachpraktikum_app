package de.fuh.michel.fachpraktikum_wi2022.qrcodescanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;


import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import de.fuh.michel.fachpraktikum_wi2022.BarcodeBoxView;

public class QrCodeAnalyzer implements ImageAnalysis.Analyzer {

    private Context context;
    private BarcodeBoxView barcodeBoxView;
    private Float previewViewWidth;
    private Float previewViewHeight;

    private float scaleX = 1f;
    private float scaleY = 1f;

    private float translateX(float x) {
        return x * scaleX;
    }

    private float translateY(float y) {
        return y * scaleY;
    }

    private RectF adjustBoundingRect(Rect rect) {
        return new RectF(
                translateX(rect.left),
                translateY(rect.top),
                translateX(rect.right),
                translateY(rect.bottom));
    }

    public QrCodeAnalyzer(Context context, BarcodeBoxView barcodeBoxView, Float previewViewWidth, Float previewViewHeight) {
        this.context = context;
        this.barcodeBoxView = barcodeBoxView;
        this.previewViewWidth = previewViewWidth;
        this.previewViewHeight = previewViewHeight;
    }

    @Override
    @SuppressLint("UnsafeOptInUsageError")
    public void analyze(@NonNull ImageProxy image) {
        Image img = image.getImage();
        if (img != null) {
            InputImage inputImage = InputImage.fromMediaImage(img, image.getImageInfo().getRotationDegrees());

            // Process image searching for barcodes
            BarcodeScannerOptions options = new BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
                    .build();

            BarcodeScanner scanner = BarcodeScanning.getClient(options);

            scanner.process(inputImage)
                    .addOnSuccessListener(barcodes -> {
                        if (!barcodes.isEmpty()) {
                            for (Barcode barcode : barcodes) {
                                // Handle received barcodes...
                                Toast.makeText(context,
                                                "Value: " + barcode.getRawValue(),
                                                Toast.LENGTH_SHORT
                                        )
                                        .show();
                                // Update bounding rect
                                if (barcode.getBoundingBox() != null) {
                                    barcodeBoxView.setRect(
                                            adjustBoundingRect(barcode.getBoundingBox())
                                    );
                                }
                            }
                        } else {
                            // Remove bounding rect
                            barcodeBoxView.setRect(new RectF());
                        }
                    })
                    .addOnFailureListener(e -> {
                        // handle failure
                    });
        }

        image.close();
    }
}