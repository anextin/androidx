package com.example.kafein.fileread;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {

    private static final String FILE_NAME = "externalfile.txt";
    private static final String DIR_NAME = "externaldir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button writeButton = (Button) findViewById(R.id.writeButton);
        Button readButton = (Button) findViewById(R.id.readButton);
        final EditText editTextContent = (EditText) findViewById(R.id.editTextContent);


        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
                    Toast.makeText(MainActivity2.this,
                            "External Storage is not Available or is Read Only",
                            Toast.LENGTH_LONG).show();
                }else{
                    String message = editTextContent.getText().toString();
                    try {
                        FileOutputStream fos = new FileOutputStream(new File(getExternalFilesDir(DIR_NAME), FILE_NAME));
                        fos.write(message.getBytes());
                        fos.close();
                        Toast.makeText(MainActivity2.this,
                                "Data written to externalfile.txt in external storage",
                                Toast.LENGTH_SHORT).show();
                        editTextContent.setText("");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        });
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExternalStorageAvailable()) {
                    Toast.makeText(MainActivity2.this,
                            "External Storage is not Available",
                            Toast.LENGTH_LONG).show();
                }else{
                    try {
                        String message = "";

                        FileInputStream fis = new FileInputStream(
                                new File(getExternalFilesDir(DIR_NAME), FILE_NAME));
                        int c;
                        while ((c = fis.read()) != -1) {
                            message += String.valueOf((char) c);
                        }
                        editTextContent.setText(message);
                        fis.close();
                        Toast.makeText(MainActivity2.this,
                                "Data read from externalfile.txt in external storage",
                                Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }
    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

}
