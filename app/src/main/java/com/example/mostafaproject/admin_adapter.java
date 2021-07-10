package com.example.mostafaproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class admin_adapter extends RecyclerView.Adapter<com.example.mostafaproject.admin_adapter.MyViewHolder> {

    Context context;
    List<Address> addresses;
    ArrayList<adminObject> ad=new ArrayList<>();
    Geocoder geocoder;
    deleteNode dl;
    public admin_adapter(Context c ,deleteNode dl)
    {


        context = c;
        this.dl=dl;
    }

    public void setAd(ArrayList<adminObject> ad) {
        this.ad = ad;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        try {
            geocoder  = new Geocoder(context, Locale.getDefault());
            addresses = geocoder.getFromLocation(Double.parseDouble(ad.get(position).getLocation1()),Double.parseDouble(ad.get(position).getLocation2()),
                    1000);
            Log.d("aaaaaaa", "onBindViewHolder: "+addresses);
            String address = addresses.get(position).getAddressLine(position); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            holder.city.setText(address);

        } catch (IOException e) {
            e.printStackTrace();
        }



        holder.location1.setText(ad.get(position).getLocation1());

        holder.location2.setText(ad.get(position).getLocation2());

holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Hello admin").setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                }
        )
                .setMessage("Are You Sure Want To Delete This Pump ? ")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dl.delete(ad.get(position).getK(), ad.get(position).getUsrid());

                    }
                });

        builder.create().show();
        return false;

    }

});



    }

    @Override
    public int getItemCount() {
        return ad.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView location1,location2,city;


        public MyViewHolder(View itemView) {

            super(itemView);

            location1 = (TextView) itemView.findViewById(R.id.lattitudeInfo);
            location2 = (TextView) itemView.findViewById(R.id.LongtitudeInfo);
            city=(TextView)itemView.findViewById(R.id.cityInfo);

        }


    }

}
