package sakuraya.bousai_g.services;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by sakuraya on 2017/01/21.
 */

public class CommonService {

    // GETリクエストでJSONデータを取得して返す
    public static JSONArray getJsonResponse(InputStream in) throws IOException, UnsupportedEncodingException {

        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        StringBuilder sb = new StringBuilder();

        String result;
        while ((result = br.readLine()) != null) {
            sb.append(result);
        }
        try {
            return new JSONArray(sb.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
