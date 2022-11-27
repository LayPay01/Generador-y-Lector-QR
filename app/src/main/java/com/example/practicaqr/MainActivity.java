package com.example.practicaqr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    EditText Text;
    ImageView QRCode;
    TextView TextQR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextQR = findViewById(R.id.TextQR);
        QRCode = findViewById(R.id.QRCode);
        Text = findViewById(R.id.Text);
    }

    //Para el generador QR
    public void Generar(View view){
        try {
            //GENERA QR
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(Text.getText().toString(), BarcodeFormat.QR_CODE, 200, 200);
            QRCode.setImageBitmap(bitmap);
        }catch (Exception e){
            //GENERA QR
            e.printStackTrace();
        }
    }

    //Para el lector QR.
    public void LectorQR(View view){
        if(view.getId() == R.id.qr){
            new IntentIntegrator(this).initiateScan();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Llamar a la información.
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        //Obtener la información en un String
        String datos = result.getContents();
        TextQR.setText(datos);
    }
}