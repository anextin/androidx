package com.example.kafein.otogalerim;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Kafein on 7/19/2018.
 */

public class AlertDialogClass {

    public  void ilanlarimAlertDialog(Activity activity, String uye_id)  //silinicek olanın ilan_id sini almamız lazım
    {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view= inflater.inflate(R.layout.alertlayout,null);



        Button button= view.findViewById(R.id.buton);
        Button buttonCık= view.findViewById(R.id.buton2);

        AlertDialog.Builder alert= new AlertDialog.Builder(activity);
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog= alert.create();

        buttonCık.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
