package com.example.mostafaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class admin_panel extends AppCompatActivity implements deleteNode{
    Geocoder geocoder;

    private DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    admin_adapter adapter;
    List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
        adapter = new admin_adapter(admin_panel.this,this);
recyclerView.setAdapter(adapter);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("ploc");
    databaseReference.addValueEventListener(new ValueEventListener() {
        final ArrayList<adminObject> ad = new ArrayList<>();

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            for (DataSnapshot userDataSnapshot : dataSnapshot.getChildren()) {
                if (userDataSnapshot != null) {
                    for (DataSnapshot roomDataSnapshot : userDataSnapshot.getChildren()) {

                        adminObject s = roomDataSnapshot.getValue(adminObject.class);
                        assert s != null;


                        ad.add(s);


                    }
                }
            }
            adapter .setAd(ad);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }


    @Override
    public void delete(String k, String Usrid) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("ploc").child(Usrid).child(k);
        rootRef.removeValue();

        Intent myIntent = new Intent(getApplicationContext(),admin_panel.class);
        startActivity(myIntent);
        finish();

    }
}