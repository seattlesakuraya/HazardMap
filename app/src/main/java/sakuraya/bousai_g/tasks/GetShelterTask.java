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

import sakuraya.bousai_g.models.Shelter;
import sakuraya.bousai_g.services.CommonService;

/**
 * Created by sakuraya on 2017/01/22.
 */

/**
 * 周辺の避難所の情報を取得
 */
public class GetShelterTask extends AsyncTask<Double, Void, JSONArray> {

    private static GoogleMap map;

    public GetShelterTask(GoogleMap map) {
        this.map = map;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected JSONArray doInBackground(Double... params) {
        HttpURLConnection con = null;
        double latitude = params[0];
        double longitude = params[1];
        URL url = null;
        String urlSt = "https://bousai4-sasurai-usagi3.c9users.io/shelter?latitude=" + latitude + "&longitude=" + longitude;

        try {
            url = new URL(urlSt);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(false);

            con.connect();
            InputStream in = con.getInputStream();
            JSONArray shelters = CommonService.getJsonResponse(in);
            Log.d("bousai_g", shelters.toString());
            return shelters;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONArray shelters) {
        super.onPostExecute(shelters);
        plotShelters(shelters);
    }

    // 取得した避難所を地図上にプロット
    public void plotShelters(JSONArray shelters) {
        try {
            if(shelters != null) {
                int count = shelters.length();
                for (int i = 0; i < count; i++) {
                    JSONObject json = new JSONObject(shelters.getString(i));
                    Shelter shelter = new Shelter(json);
                    shelter.plot(map);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
