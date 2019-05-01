package com.mobilhanem.timepickerkullanimi;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Anasayfa extends Activity {
	Button saatButton,tarihButton;
	TextView saatTextView,tarihTextView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anasayfa);
		
		//Oluşturduğumuz XML'den gerekli referansları atıyoruz
		saatButton = (Button) findViewById(R.id.button1);
		tarihButton = (Button) findViewById(R.id.button2);
		saatTextView = (TextView) findViewById(R.id.textView1);
		tarihTextView = (TextView) findViewById(R.id.textView2);
		
		
		saatButton.setOnClickListener(new View.OnClickListener() {//saatButona Click Listener ekliyoruz
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar mcurrentTime = Calendar.getInstance();//
	            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);//Güncel saati aldık
	            int minute = mcurrentTime.get(Calendar.MINUTE);//Güncel dakikayı aldık
	            TimePickerDialog timePicker; //Time Picker referansımızı oluşturduk
	            
	          //TimePicker objemizi oluşturuyor ve click listener ekliyoruz
	            timePicker = new TimePickerDialog(Anasayfa.this, new TimePickerDialog.OnTimeSetListener() { 
	                @Override
	                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
	                	saatTextView.setText( selectedHour + ":" + selectedMinute);//Ayarla butonu tıklandığında textview'a yazdırıyoruz
	                }
	            }, hour, minute, true);//true 24 saatli sistem için
	            timePicker.setTitle("Saat Seçiniz");
	            timePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", timePicker);
	            timePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", timePicker);

	            timePicker.show();
			}
		});
		
		tarihButton.setOnClickListener(new View.OnClickListener() {//tarihButona Click Listener ekliyoruz
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar mcurrentTime = Calendar.getInstance();
	            int year = mcurrentTime.get(Calendar.YEAR);//Güncel Yılı alıyoruz
	            int month = mcurrentTime.get(Calendar.MONTH);//Güncel Ayı alıyoruz
	            int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);//Güncel Günü alıyoruz

	            DatePickerDialog datePicker;//Datepicker objemiz
	            datePicker = new DatePickerDialog(Anasayfa.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
	                	tarihTextView.setText( dayOfMonth + "/" + monthOfYear+ "/"+year);//Ayarla butonu tıklandığında textview'a yazdırıyoruz

					}
				},year,month,day);//başlarken set edilcek değerlerimizi atıyoruz 
	            datePicker.setTitle("Tarih Seçiniz");
	            datePicker.setButton(DatePickerDialog.BUTTON_POSITIVE, "Ayarla", datePicker);
	            datePicker.setButton(DatePickerDialog.BUTTON_NEGATIVE, "İptal", datePicker);

	            datePicker.show();
	            
			}
		});
	}

}
