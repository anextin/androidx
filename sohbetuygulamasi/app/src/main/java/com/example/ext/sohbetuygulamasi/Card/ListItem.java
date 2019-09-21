package com.example.ext.sohbetuygulamasi.Card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ext.sohbetuygulamasi.R;

public class ListItem extends RecyclerView.ViewHolder {
  TextView textView;
  static ImageView  cardimage;

  public ListItem(View itemView) {
    super(itemView);
    textView = itemView.findViewById(R.id.text);
    cardimage=itemView.findViewById(R.id.cardimage);
  }

  public void bind(int i) {
    textView.setText(String.valueOf(i));
  }
}
