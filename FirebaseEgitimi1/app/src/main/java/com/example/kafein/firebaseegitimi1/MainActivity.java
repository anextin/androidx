package com.example.kafein.firebaseegitimi1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference ref1;
    EditText key,value;
    Button ekle;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ref1=database.getReference("adi");
        ref1.setValue("arda");


        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("value",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void tanimla()
    {
        database=FirebaseDatabase.getInstance();
        key=findViewById(R.id.key);
        value=findViewById(R.id.value);
        ekle=findViewById(R.id.ekle);
        result=findViewById(R.id.result);

    }

    public void action()
    {
        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyText = key.getText().toString();
                String valueText=value.getText().toString();
                ref1= database.getReference(keyText);
                ref1.setValue(valueText);
                key.setText("");
                value.setText("");

            }
        });
    }
}
