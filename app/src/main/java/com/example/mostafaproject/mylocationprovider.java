package com.example.mostafaproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.location.LocationCallback;

import java.util.List;

@SuppressLint("MissingPermission")
public class mylocationprovider {

    LocationManager locationManager;
    Location location;
    LocationCallback locationCallback;

    public mylocationprovider(Context context) {
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        location = null;
    }

    public boolean providerEnabled() {
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }
        public Location getCurrentLocation(LocationListener locationListener) {
        if (!providerEnabled())
            return null;


        String provider = LocationManager.GPS_PROVIDER;

        if (!locationManager.isProviderEnabled(provider))
            provider = LocationManager.NETWORK_PROVIDER;


        location = locationManager.getLastKnownLocation(provider);
        if(location==null)
            location=getBestLocation();


        if(locationListener!=null)
        {
            System.out.println("ccccccccccccccccccccccccccccccc");

            locationManager.requestLocationUpdates(provider,0,0 ,locationListener);

        }
        return location;
    }

    private Location getBestLocation()
    {
        List<String> providers=locationManager.getAllProviders();
        Location bestlocation=null;
        for(String provider: providers)
        {
            Location tmp=locationManager.getLastKnownLocation(provider);
            if(tmp==null)
                continue;

            if(bestlocation==null)
            {
                bestlocation=tmp;
            }
            else
            {
                if(tmp.getAccuracy()>bestlocation.getAccuracy())
                {
                    bestlocation=tmp;
                }
            }
        }
        Log.d("provideeer ", "onCreate: " + bestlocation);


        return bestlocation;
    }

}
