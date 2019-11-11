package com.dev.ext.sohbetuygulamasi.Acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dev.ext.sohbetuygulamasi.Adapters.MessageAdapter;
import com.dev.ext.sohbetuygulamasi.Card.CardListAdapter;
import com.dev.ext.sohbetuygulamasi.Card.ListAdapter;
import com.dev.ext.sohbetuygulamasi.Models.CardModel;
import com.dev.ext.sohbetuygulamasi.Models.Kullanicilar;
import com.dev.ext.sohbetuygulamasi.Models.MessageModel;
import com.dev.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public class CardActivity extends AppCompatActivity {

    ListAdapter adapter;
    List<Object> userKeysList;
    List<String> kullanicilarList;
    CardListAdapter cardlistadapter;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    List<MessageModel> messageModelList;
    RecyclerView chat_recycler_view,recycler_view;
    MessageAdapter messageAdapter;
    List<String> keyList;
    ArrayList<Object> objectArrayList;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


  //      adapter = new ListAdapter(keyList,CardActivity.this,CardActivity.this);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //benimkiler
        tanimla();
        kullanicilariGetir();




        SwipeableTouchHelperCallback swipeableTouchHelperCallback =
                new SwipeableTouchHelperCallback(new OnItemSwiped() {
                    @Override public void onItemSwiped() {
                        adapter.removeTopItem();
                    }

                    @Override public void onItemSwipedLeft() {
                        Log.e("SWIPE", "LEFT");
                    }

                    @Override public void onItemSwipedRight() {
                        Log.e("SWIPE", "RIGHT");
                    }

                    @Override public void onItemSwipedUp() {
                        Log.e("SWIPE", "UP");
                    }

                    @Override public void onItemSwipedDown() {
                        Log.e("SWIPE", "DOWN");
                    }
                }) {
                    @Override
                    public int getAllowedSwipeDirectionsMovementFlags(RecyclerView.ViewHolder viewHolder) {
                        return ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
                    }
                };
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeableTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new SwipeableLayoutManager().setAngle(10)
                .setAnimationDuratuion(450)
                .setMaxShowCount(3)
                .setScaleGap(0.1f)
                .setTransYGap(0));
//        recyclerView.setAdapter(adapter = new ListAdapter(keyList,CardActivity.this,CardActivity.this));

        AppCompatButton button = findViewById(R.id.swipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                itemTouchHelper.swipe(recyclerView.findViewHolderForAdapterPosition(0), ItemTouchHelper.DOWN);
            }
        });
    }

    public void tanimla()
    {
        auth= FirebaseAuth.getInstance();
        firebaseUser=auth.getCurrentUser();
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        keyList = new ArrayList<>();
    }

    public void kullanicilariGetir()
    {
        reference.child("Kullanicilar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

               Kullanicilar kullanicilar=dataSnapshot.getValue(Kullanicilar.class);
                CardModel cardModel= dataSnapshot.getValue(CardModel.class);
 //               System.out.println("adanakeyler0: "+cardModel.toString());
   //            System.out.println("adanakeyler1: "+kullanicilar.toString());
     //           System.out.println("adanakeyler2: "+ dataSnapshot.toString());
       //         System.out.println("adanakeyler4: "+ dataSnapshot.child("resim").getValue());
         //       System.out.println("adanakeyler3: "+ dataSnapshot.getKey().toString());
                if(!dataSnapshot.toString().equals(null)) {
         //           kullanicilarList.add((String) dataSnapshot.child("resim").toString());
         //           kullanicilarList.add(cardModel.toString());
          //          userKeysList.add(dataSnapshot.getKey());
                    System.out.println("adanakeyler9: "+dataSnapshot.toString());
         //           Map<String, Object> td = (HashMap<String,Object>) dataSnapshot.getValue();
          //          System.out.println("adanakeyler5: "+td.toString());

          //          GenericTypeIndicator<HashMap<String, Object>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Object>>() {};
          //          Map<String, Object> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
           //         userKeysList = new ArrayList<Object>(objectHashMap.values());
           //         System.out.println("adanakeyler6: "+userKeysList.size()+"....."+userKeysList.toString());
//                    List<Object> values = (List<Object>) td.values();
                }


        //      kullanicilarList.add(dataSnapshot.toString());
    //           adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
