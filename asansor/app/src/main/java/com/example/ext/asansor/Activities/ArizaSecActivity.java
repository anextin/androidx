package com.example.ext.asansor.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ext.asansor.BarcodeActivity;
import com.example.ext.asansor.R;
import com.google.android.gms.vision.barcode.Barcode;
import com.notbytes.barcode_reader.BarcodeReaderActivity;
import com.notbytes.barcode_reader.BarcodeReaderFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArizaSecActivity  extends AppCompatActivity implements View.OnClickListener, BarcodeReaderFragment.BarcodeReaderListener {
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;
    private TextView mTvResult;
    private TextView mTvResultHeader;
    Button manualgirisOnayButton;
    EditText manualgirisEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ariza_sec);
        findViewById(R.id.btn_fragment).setOnClickListener(this);
        mTvResult = findViewById(R.id.tv_result);
        mTvResultHeader = findViewById(R.id.tv_result_head);
        tanimla();
    }


    public void tanimla() {

        manualgirisEditText= findViewById(R.id.manualgirisEditText);
        manualgirisOnayButton= findViewById(R.id.manualgirisOnayButton);

        manualgirisOnayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!manualgirisEditText.getText().toString().equals("")) {

                    String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                    Intent intent = new Intent(ArizaSecActivity.this, ArizaOnarimDonemTarihiSecActivity.class);
                    intent.putExtra("asansorserino", manualgirisEditText.getText().toString());
                    intent.putExtra("bakimbasla", date);
                    startActivity(intent);
                    //            BakimPojo.setBinaAdi(BinaAdiEditText.getText().toString());  //geri dondugumuzde doldurulan bilgiler kalsın die

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Tum bilgileri doldur", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void addBarcodeReaderFragment() {
        BarcodeReaderFragment readerFragment = BarcodeReaderFragment.newInstance(true, false, View.VISIBLE);
        readerFragment.setListener(this);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fm_container, readerFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_fragment:
                addBarcodeReaderFragment();
                break;
        }
    }




    @Override
    public void onScanned(Barcode barcode) {
        Toast.makeText(this, barcode.rawValue, Toast.LENGTH_SHORT).show();
        mTvResultHeader.setText("Barcode value from fragment");
        mTvResult.setText(barcode.rawValue);
        if(!mTvResult.getText().toString().equals(""))
        {
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Intent intent = new Intent(ArizaSecActivity.this, ArizaActivity.class);
            intent.putExtra("asansorserino", mTvResult.getText().toString());
            intent.putExtra("bakimbasla", date);
            startActivity(intent);
        }
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(this, "Camera permission denied!", Toast.LENGTH_LONG).show();
    }
}
