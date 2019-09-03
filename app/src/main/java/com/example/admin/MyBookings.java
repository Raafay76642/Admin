package com.example.admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookings extends AppCompatActivity {
    private RecyclerView recyclerView;
    private B_Adapter PAdapter;
    private List<Model_Booking> bList;
    Query query;
    FirebaseAuth firebaseAuth;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);
        recyclerView = findViewById(R.id.b_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bList = new ArrayList<>();
        PAdapter = new B_Adapter(this, bList);
        recyclerView.setAdapter(PAdapter);
        firebaseAuth=FirebaseAuth.getInstance();
        key=firebaseAuth.getCurrentUser().getUid();
        query= FirebaseDatabase.getInstance().getReference().child("Bookings").orderByChild("pid").equalTo(key);
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            bList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Model_Booking modelBooking = snapshot.getValue(Model_Booking.class);
                    bList.add(modelBooking);
                }
                PAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
