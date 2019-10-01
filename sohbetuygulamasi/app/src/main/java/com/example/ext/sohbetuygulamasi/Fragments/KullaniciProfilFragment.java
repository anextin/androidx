package com.example.ext.sohbetuygulamasi.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.example.ext.sohbetuygulamasi.R;
import com.example.ext.sohbetuygulamasi.Utils.ChangeFragment;
import com.example.ext.sohbetuygulamasi.Utils.RandomName;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class KullaniciProfilFragment extends Fragment {

    Spinner ilcespinner,irkspinner,cinsiyetspinner;
    String imageUrl;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    View view;
    EditText kullaniciIsmi,input_egitim,input_dogumtarih,input_hakkimda;
    CircleImageView profile_image;
    Button bilgiGuncelleButton,bilgiArkadasButon,bilgiIstekButonu;
    StorageReference storageReference;
    FirebaseStorage firebaseStorage;
    Bitmap  selectedImage;
    String spinner_ilce;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_kullanici_profil, container, false);
        ilcespinner =view.findViewById(R.id.ilceSpinner);
        irkspinner =view.findViewById(R.id.irkSpinner);
        cinsiyetspinner =view.findViewById(R.id.cinsiyetSpinner);



        tanimla();
        bilgileriGetir();
        return  view;

    }


    public void tanimla()
    {
        final String ilceler[]={"Adalar","Arnavutköy","Ataşehir","Avcılar","Bağcılar","Bahçelievler","Bakırköy","Başaksehir","Bayrampaşa","Beşiktaş","Beykoz","Beylikdüzü","Beyoğlu","Büyükçekmece","Çatalca","Çekmeköy","Esenler","Esenyurt","Eyüp","Fatih","Gaziosmanpaşa","Güngören","Kadıköy","Kağıthane","Kartal","Küçükçekmece","Maltepe","Pendik","Sancaktepe","Sarıyer","Şile","Silivri","Şişli","Sultanbeyli","Sultangazi","Tuzla","Ümraniye","Üsküdar","Zeytinburnu"};

        final String irk[]={"golden","pug","doberman","kurt"};

        final String cinsiyet[]={"Erkek","Dişi"};

        bilgiArkadasButon=view.findViewById(R.id.bilgiArkadasButon);
        bilgiIstekButonu=view.findViewById(R.id.bilgiIstekButonu);

        kullaniciIsmi= view.findViewById(R.id.kullaniciIsmi);
        input_egitim= view.findViewById(R.id.input_egitim);
        input_dogumtarih= view.findViewById(R.id.input_dogumTarihi);
        //   input_hakkimda= view.findViewById(R.id.input_hakkimda);
        profile_image= view.findViewById(R.id.profile_image);
        bilgiGuncelleButton=view.findViewById(R.id.bilgiGuncelleButon);






        ArrayAdapter<String> ilcelerdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, ilceler);
        ilcelerdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ilcespinner.setAdapter(ilcelerdataAdapter);

        ArrayAdapter<String> irkdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, irk);
        irkdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        irkspinner.setAdapter(irkdataAdapter);

        ArrayAdapter<String> cinsiyetdataAdapter= new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, cinsiyet);
        cinsiyetdataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cinsiyetspinner.setAdapter(cinsiyetdataAdapter);

        firebaseStorage= FirebaseStorage.getInstance();
        storageReference= firebaseStorage.getReference();//resim ekleme icin

        bilgiGuncelleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guncelle();
            }
        });

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galeriAc();
            }
        });


        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        database= FirebaseDatabase.getInstance();
        reference=database.getReference().child("Kullanicilar").child(auth.getUid());

        bilgiArkadasButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment= new ChangeFragment((getContext()));
                changeFragment.change(new ArkadaslarFragment());
            }
        });

        bilgiIstekButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeFragment changeFragment= new ChangeFragment((getContext()));
                changeFragment.change(new BildirimFragment());
            }
        });
    }







    public void galeriAc(){

        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);   //galeri acıo
        startActivityForResult(intent,5);
    }

    public void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        if(requestCode==5 && resultCode== Activity.RESULT_OK)
        {
            Uri filePath = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getActivity().getApplicationContext().getContentResolver().openInputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            selectedImage = BitmapFactory.decodeStream(imageStream);

            selectedImage = getResizedBitmap(selectedImage, 400);// 400 is for example, replace with desired size


            saveToInternalStorage(selectedImage);
            isExternalStorageWritable();

            Uri uri = Uri.fromFile(new File(saveToInternalStorage(selectedImage)));
            System.out.println("mersinyersin9: "+"..."+saveToInternalStorage(selectedImage)+"....||||...."+filePath+"....||||...."+uri);


            StorageReference ref =storageReference.child("kullaniciresimleri").child(RandomName.getSaltString()+".jpg");
            ref.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(getContext(),"resim guncellendi...", Toast.LENGTH_LONG).show();


                        //     String hakkimda=input_hakkimda.getText().toString();
                        String spinner_ilce=ilcespinner.getSelectedItem().toString();
                        int spinner_ilceNum= (int) ilcespinner.getSelectedItemId();

                        String spinner_irk=irkspinner.getSelectedItem().toString();
                        int spinner_irkNum= (int) irkspinner.getSelectedItemId();

                        String spinner_cinsiyet=cinsiyetspinner.getSelectedItem().toString();
                        int spinner_cinsiyetNum= (int) cinsiyetspinner.getSelectedItemId();
                        String isim=kullaniciIsmi.getText().toString();
                        String egitim=input_egitim.getText().toString();
                        String dogum=input_dogumtarih.getText().toString();
//                        String hakkimda=input_hakkimda.getText().toString();


                        reference=database.getReference().child("Kullanicilar").child(auth.getUid());
                        Map map = new HashMap();

                        map.put("isim",isim);
                        map.put("egitim",egitim);
                        map.put("dogumtarih",dogum);
                        map.put("ilce",spinner_ilce);
                        map.put("ilceNum",spinner_ilceNum);
                        map.put("irk",spinner_irk);
                        map.put("irkNum",spinner_irkNum);
                        map.put("cinsiyet",spinner_cinsiyet);
                        map.put("cinsiyetNum",spinner_cinsiyetNum);
                        map.put("state",true);
                        map.put("resim",task.getResult().getDownloadUrl().toString());
                        map.put("deneme",saveToInternalStorage(selectedImage).toString());

                        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    //                       ChangeFragment fragment = new ChangeFragment(getContext());
                                    //                         fragment.change(new KullaniciProfilFragment());
                                    Toast.makeText(getContext(), "bilgiler basarili insert edildi...", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getContext(), "guncellenmedi..", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }

                    else
                    {
                        Toast.makeText(getContext(),"resim guncellenmedi..", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }





    public void bilgileriGetir()
    {
        reference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Kullanicilar k1= dataSnapshot.getValue(Kullanicilar.class);
                Log.i("bilgiler",k1.toString());
                // alternatif alma yontemi        //    String adi = dataSnapshot.child("isim").getValue().toString();
                //      String egitim = dataSnapshot.child("egitim").getValue().toString();

                System.out.println("antalya: "+k1.getIsim() );
                if(k1.getIsim().equals("null"))
                {
                    kullaniciIsmi.setText("");
                }
                else
                {
                    kullaniciIsmi.setText(k1.getIsim());
                }
                if(k1.getEgitim().equals("null"))
                {
                    input_egitim.setText("");
                }
                else
                {
                    input_egitim.setText(k1.getEgitim());
                }if(k1.getDogumtarih().equals("null"))
                {
                    input_dogumtarih.setText("");
                }
                else
                {
                    input_dogumtarih.setText(k1.getDogumtarih());
                }
                //            input_egitim.setText(k1.getEgitim());
                //          input_dogumtarih.setText(k1.getDogumtarih());
//                input_hakkimda.setText(k1.getHakkimda());


                String ilceNum=k1.getIlceNum().toString();
                Integer ilceNumresult = Integer.valueOf(ilceNum);
                //           String spin =k1.getIlce().toString();
                //           int num = (int) spinner.getSelectedItemId();
                ilcespinner.setSelection(ilceNumresult);

                if(k1.getIrkNum()==null)
                {
                    irkspinner.setSelection(0);
                }
                else {
                    String irkNum = k1.getIrkNum().toString();
                    Integer irkNumresult = Integer.valueOf(irkNum);
                    irkspinner.setSelection(irkNumresult);
                }

                if(k1.getCinsiyetNum()==null)
                {
                    cinsiyetspinner.setSelection(0);
                }
                else {
                    String cinsiyetNum = k1.getCinsiyetNum().toString();
                    Integer cinsiyetNumresult = Integer.valueOf(cinsiyetNum);
                    cinsiyetspinner.setSelection(cinsiyetNumresult);
                }






                imageUrl=k1.getResim();

                if(!k1.getResim().equals("null"))
                {
                    Picasso.get().load(k1.getResim()).into(profile_image);
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }

    public void guncelle()
    {
        String isim=kullaniciIsmi.getText().toString();
        String egitim=input_egitim.getText().toString();
        String dogum=input_dogumtarih.getText().toString();
        //     String hakkimda=input_hakkimda.getText().toString();
        String spinner_ilce=ilcespinner.getSelectedItem().toString();
        int spinner_ilceNum= (int) ilcespinner.getSelectedItemId();

        String spinner_irk=irkspinner.getSelectedItem().toString();
        int spinner_irkNum= (int) irkspinner.getSelectedItemId();

        String spinner_cinsiyet=cinsiyetspinner.getSelectedItem().toString();
        int spinner_cinsiyetNum= (int) cinsiyetspinner.getSelectedItemId();




        reference=database.getReference().child("Kullanicilar").child(auth.getUid());
        Map map = new HashMap();

        map.put("isim",isim);
        map.put("egitim",egitim);
        map.put("dogumtarih",dogum);
        map.put("ilce",spinner_ilce);
        map.put("ilceNum",spinner_ilceNum);
        map.put("irk",spinner_irk);
        map.put("irkNum",spinner_irkNum);
        map.put("cinsiyet",spinner_cinsiyet);
        map.put("cinsiyetNum",spinner_cinsiyetNum);
        map.put("state",true);
        //       map.put("hakkimda",hakkimda);
        if(imageUrl.equals("null"))
        {
            map.put("resim","null");
        }

        else
        {
            map.put("resim",imageUrl);
        }

        reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    ChangeFragment fragment = new ChangeFragment(getContext());
                    fragment.change(new KullaniciProfilFragment());
                    Toast.makeText(getContext(), "Bilgiler Başarıyla Güncellendi...", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Beklenmedik Bir Hata Oluştu..", Toast.LENGTH_LONG).show();
                }

            }
        });
    }



    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getActivity().getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.toString();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
