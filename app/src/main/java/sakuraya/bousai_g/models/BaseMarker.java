package sakuraya.bousai_g.models;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

/**
 * Created by sakuraya on 2017/01/22.
 */

public class BaseMarker {


    private double latitude;
    private double longitude;
    private int iconId = 0;

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

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
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
        setIconImage(map);
    }

    // 地図上にプロット
    public void plot(GoogleMap map, String title) {
        LatLng pos = new LatLng(this.latitude, this.longitude);
        map.addMarker(new MarkerOptions().position(pos).title(title));
        setIconImage(map);
    }

    // 地図上にプロット
    public void plot(GoogleMap map, String title, String description) {
        LatLng pos = new LatLng(this.latitude, this.longitude);
        map.addMarker(new MarkerOptions().position(pos).title(title).snippet(description).icon(BitmapDescriptorFactory.fromResource(iconId)));
//        setIconImage(map);
    }

    // アイコン画像セット
    public void setIconImage(GoogleMap map) {
        if(iconId != 0) {
            BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(iconId);

            // 貼り付設定
            GroundOverlayOptions overlayOptions = new GroundOverlayOptions();
            overlayOptions.image(descriptor);

            //　public GroundOverlayOptions anchor (float u, float v)
            // (0,0):top-left, (0,1):bottom-left, (1,0):top-right, (1,1):bottom-right
            overlayOptions.anchor(0.5f, 0.5f);

            // 張り付け画像の大きさ メートル単位
            // public GroundOverlayOptions	position(LatLng location, float width, float height)
            overlayOptions.position(new LatLng(latitude, longitude), 300f, 300f);

            // マップに貼り付け・アルファを設定
            GroundOverlay overlay = map.addGroundOverlay(overlayOptions);

            // 透明度
            overlay.setTransparency(0.0F);
        }
    }
}
