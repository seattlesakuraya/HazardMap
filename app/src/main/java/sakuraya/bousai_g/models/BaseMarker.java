package sakuraya.bousai_g.models;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

/**
 * Created by sakuraya on 2017/01/22.
 */

public class BaseMarker {


    private double latitude;
    private double longitude;

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public BaseMarker(JSONObject json) {
        try {
            this.latitude = Double.parseDouble(json.getString("latitude"));
            this.longitude = Double.parseDouble(json.getString("longitude"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 地図上にプロット
    public void plot(GoogleMap map) {
        LatLng pos = new LatLng(this.latitude, this.longitude);
        map.addMarker(new MarkerOptions().position(pos));
    }

    // 地図上にプロット
    public void plot(GoogleMap map, String description) {
        LatLng pos = new LatLng(this.latitude, this.longitude);
        map.addMarker(new MarkerOptions().position(pos).title(description));
    }
}
