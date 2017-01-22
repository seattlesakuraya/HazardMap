package sakuraya.bousai_g.tasks;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sakuraya on 2017/01/22.
 */


/**
 * 現在地の状況を登録する
 */
public class PostMarkerTask extends AsyncTask<String, Void, Boolean> {

    private Context context;

    public PostMarkerTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        HttpURLConnection con = null;
        URL url = null;
        String urlSt = "https://bousai4-sasurai-usagi3.c9users.io/information";

        try {
            url = new URL(urlSt);
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);

            // 入力値の取得
            String latitude = params[0];
            String longitude = params[1];
            String comment = params[2];
            String status = params[3];

            // POSTするパラメーターの作成
            StringBuilder data = new StringBuilder("");
            data.append("latitude=");
            data.append(latitude);
            data.append("&longitude=");
            data.append(longitude);
            data.append("&comment=");
            data.append(comment);
            data.append("&status=");
            data.append(status);

            OutputStreamWriter ow = new OutputStreamWriter(con.getOutputStream());
            BufferedWriter bw = new BufferedWriter(ow);
            bw.write(data.toString());
            bw.close();
            ow.close();
            con.connect();

            // HTTPレスポンス取得
            int statusCode = con.getResponseCode();
            Log.d("bousai_g:", "[post marker request] resulted in code " + Integer.toString(statusCode));
            return true;
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(con != null) {
                con.disconnect();
            }
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        Toast.makeText(context.getApplicationContext(), "登録しました！", Toast.LENGTH_SHORT);
    }

}
