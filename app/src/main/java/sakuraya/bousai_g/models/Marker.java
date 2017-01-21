package sakuraya.bousai_g.models;

/**
 * Created by sakuraya on 2017/01/21.
 */

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

import java.util.Date;

/**
 * 登録した場所
 */
public class Marker extends BaseMarker {

    // ステータス
    private static final int WARNING = 0; // 注意
    private static final int DANGER = 1; // 危険
    private static final int FIRE = 2; // 火事
    private static final int RESCUE = 3; // 要救助者あり
    private static final int BLOCKED = 4; // 通行できない
    private static final int OTHERS = 5; // その他

    private String comment;
    private int status;
    private Date created_at;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Marker(JSONObject json) {
        super(json);
        try {
            this.comment = json.getString("comment");
            this.status = Integer.parseInt(json.getString("status"));
            this.created_at = new Date();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 地図上にプロット
    @Override
    public void plot(GoogleMap map) {
        String description = this.comment + "¥n登録日時：" + this.created_at;
        super.plot(map,description);
    }


}
