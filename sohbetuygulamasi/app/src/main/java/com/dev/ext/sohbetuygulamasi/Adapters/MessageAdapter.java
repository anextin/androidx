package com.dev.ext.sohbetuygulamasi.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.ext.sohbetuygulamasi.Models.MessageModel;
import com.dev.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    List<String> userKeysList;
    Activity activity;
    Context context;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    String userId="";
    List<MessageModel> messageModelList;
    Boolean state;
    int view_type_sent=1;
    int view_type_received=2;

    public MessageAdapter(List<String> userKeysList, Activity activity, Context context, List<MessageModel> messageModelList) {
        this.userKeysList = userKeysList;
        this.activity = activity;
        this.context = context;
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        auth= FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        userId=firebaseUser.getUid();
        this.messageModelList =messageModelList;
        state=false;

    }

    //layout tanımlaması yapılacak

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==view_type_sent)
        {
            view= LayoutInflater.from(context).inflate(R.layout.message_sent_layout ,parent,false);
            return new ViewHolder(view);
        }
        else
        {
            view= LayoutInflater.from(context).inflate(R.layout.message_received_layout ,parent,false);}
        return new ViewHolder(view);
    }

    //viewlara setlemeler yapılıcak
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        holder.messageText.setText(messageModelList.get(position).getText());
        holder.messageDate.setText(messageModelList.get(position).getTime());

    }

    //adaptera olusturulucak olan listenin size ı burda olucak
    @Override
    public int getItemCount() {
        return messageModelList.size();
    }


    //viewların tanımlanma işleri
    public class ViewHolder extends RecyclerView.ViewHolder
    {


        TextView messageText;
        TextView messageDate;




        CircleImageView friend_state_img,friend_image;


        ViewHolder(View itemView)
        {
            super(itemView);

            if(state==true) {
                messageText = (TextView) itemView.findViewById(R.id.message_sent_text);

                messageDate= itemView.findViewById(R.id.message_sent_date);

            }
            else
            {
                messageText = (TextView) itemView.findViewById(R.id.message_reveived_text);

                messageDate= itemView.findViewById(R.id.message_reveived_date);

            }
        }


    }

    @Override
    public int getItemViewType(int position) {   //saglı sollu yazılması bunun ssayesınde

        if (messageModelList.get(position).getFrom().equals(userId))
        {
            state = true;
            return view_type_sent;
        }
        else
        {
            state =false;
            return view_type_received;
        }
    }

}
