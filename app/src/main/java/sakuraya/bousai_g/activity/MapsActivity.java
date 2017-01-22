package sakuraya.bousai_g.activity;

import android.app.DialogFragment;
import android.app.Service;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import sakuraya.bousai_g.R;
import sakuraya.bousai_g.tasks.GetMarkerTask;
import sakuraya.bousai_g.tasks.GetShelterTask;

import static sakuraya.bousai_g.R.id.map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    // 更新時間(目安)
    private static final int LOCATION_UPDATE_MIN_TIME = 10000;
    // 更新距離(目安)
    private static final int LOCATION_UPDATE_MIN_DISTANCE = 50;

    private LocationManager mLocationManager;

    private static double currentLatitude;
    private static double currentLongitude;

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    public double getCurrentLongitude() {
        return currentLongitude;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        mLocationManager = (LocationManager)this.getSystemService(Service.LOCATION_SERVICE);

        // 場所を登録するボタン
        Button registerBtn = (Button)this.findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new PostMarkerDialog();
                newFragment.show(getFragmentManager(), "post marker");

//                int WARNING = 0;
//                PostMarkerTask postMarkerTask = new PostMarkerTask();
//                postMarkerTask.execute(
//                        Double.toString(currentLatitude),
//                        Double.toString(currentLongitude),
//                        "this is a text",
//                        Integer.toString(WARNING)
//                );
//                GetMarkerTask getMarkerTask = new GetMarkerTask(mMap);
//                getMarkerTask.execute();
            }
        });
    }


    // Mapが準備できたら呼ばれる。これが呼ばれてからマーカー追加したりカメラ変えたりできる
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        requestLocationUpdates();
    }

    // 現在地の位置情報取得したら呼ばれる
    @Override
    public void onLocationChanged(Location location) {
        showLocation(location);
    }

    // プロバイダーに何か変更があったら呼ばれる
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("bousai_g", provider +"が圏外になっていて取得できません。");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("bousai_g", "一時的に" + provider + "が利用できません。");
                break;
            case LocationProvider.AVAILABLE:
                if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
                    Log.d("bousai_g", provider + "が利用可能になりました。");
                    requestLocationUpdates();
                }
                break;
        }
    }

    // ユーザー操作でプロバイダーが有効にされた時呼ばれる
    @Override
    public void onProviderEnabled(String provider) {
        Log.d("bousai_g", provider + "が有効になりました。");
        if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
            requestLocationUpdates();
        }
    }

    // ユーザー操作でプロバイダーが無効にされた時呼ばれる
    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
            Log.d("bousai_g", provider + "が無効になりました。");
        }
    }

    // 現在地情報の取得
    private void requestLocationUpdates() {
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (isNetworkEnabled) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME,
                        LOCATION_UPDATE_MIN_DISTANCE,
                        this);
            }
            Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                showLocation(location);
                plotCurrentLocation(location.getLatitude(), location.getLongitude());
            }
        } else {
            Log.d("bousai_g", "Networkが無効になっています。");
        }
    }

    // 現在の位置情報をログに表示
    private void showLocation(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        Log.d("bousai_g", "現在地の緯度 : " + String.valueOf(latitude));
        Log.d("bousai_g", "現在地の経度: " + String.valueOf(longitude));
    }

    // 現在の位置情報をマーカーで地図上に表示
    private void plotCurrentLocation(double latitude, double longitude) {
        currentLatitude = latitude;
        currentLongitude = longitude;

        LatLng here = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(here).title("I am here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        // 避難所情報を取得
        GetShelterTask getShelterTask = new GetShelterTask(mMap);
        getShelterTask.execute(latitude, longitude);
        //
        GetMarkerTask getMarkerTask = new GetMarkerTask(mMap);
        getMarkerTask.execute(Double.toString(currentLatitude), Double.toString(currentLongitude));
    }



}
