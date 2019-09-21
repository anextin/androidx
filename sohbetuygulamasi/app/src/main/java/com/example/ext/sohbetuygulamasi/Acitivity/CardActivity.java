package com.example.ext.sohbetuygulamasi.Acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ext.sohbetuygulamasi.Adapters.MessageAdapter;
import com.example.ext.sohbetuygulamasi.Adapters.UserAdapter;
import com.example.ext.sohbetuygulamasi.Card.CardListAdapter;
import com.example.ext.sohbetuygulamasi.Card.ListAdapter;
import com.example.ext.sohbetuygulamasi.Models.MessageModel;
import com.example.ext.sohbetuygulamasi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import swipeable.com.layoutmanager.OnItemSwiped;
import swipeable.com.layoutmanager.SwipeableLayoutManager;
import swipeable.com.layoutmanager.SwipeableTouchHelperCallback;
import swipeable.com.layoutmanager.touchelper.ItemTouchHelper;

public class CardActivity extends AppCompatActivity {

    private ListAdapter adapter;
    List<String> userKeysList;
    CardListAdapter cardlistadapter;
    DatabaseReference reference,referenceSeen;
    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth auth;
    List<MessageModel> messageModelList;
    RecyclerView chat_recycler_view;
    MessageAdapter messageAdapter;
    List<String> keyList;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        tanimla();
        adapter = new ListAdapter();
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
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
        recyclerView.setAdapter(adapter = new ListAdapter());

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
        firebaseDatabase= FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        keyList = new ArrayList<>();
    }
}
