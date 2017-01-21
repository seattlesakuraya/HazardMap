package sakuraya.bousai_g.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import sakuraya.bousai_g.models.Marker;
import sakuraya.bousai_g.services.CommonService;

/**
 * Created by sakuraya on 2017/01/22.
 */

/**
 * みんなが登録した場所情報の取得
 */
public class GetMarkerTask extends AsyncTask<String, Void, JSONArray> {

    private static GoogleMap map;

    public GetMarkerTask(GoogleMap map) {
        this.map = map;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected JSONArray doInBackground(String... params) {
        HttpURLConnection con = null;
        URL url = null;
        String urlSt = "https://bousai4-sasurai-usagi3.c9users.io/information" ;

        try {
            url = new URL(urlSt);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(false);

            con.connect();
            InputStream in = con.getInputStream();
            JSONArray markers = CommonService.getJsonResponse(in);
            Log.d("bousai_g", markers.toString());
            return markers;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray markers) {
        super.onPostExecute(markers);
        plotMarkers(markers);
    }

    // 取得した場所情報を地図上にプロット
    public void plotMarkers(JSONArray markers) {
        try {
            if(markers != null) {
                int count = markers.length();
                for (int i = 0; i < count; i++) {
                    JSONObject json = new JSONObject(markers.getString(i));
                    Marker marker = new Marker(json);
                    marker.plot(map);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
