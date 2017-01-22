package sakuraya.bousai_g.models;

/**
 * Created by sakuraya on 2017/01/21.
 */

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import sakuraya.bousai_g.R;

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

    // アイコン
    private static final int WARNING_ICON = R.drawable.warning_m;
    private static final int DANGER_ICON = R.drawable.danger_m;
    private static final int FIRE_ICON = R.drawable.fire_m;
    private static final int RESCUE_ICON = R.drawable.rescue_m;
    private static final int BLOCKED_ICON = R.drawable.blocked_m;
    private static final int OTHERS_ICON = R.drawable.others_m;

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
            switch(this.status) {
                case WARNING:
                    this.setIconId(WARNING_ICON);
                    break;
                case DANGER:
                    this.setIconId(DANGER_ICON);
                    break;
                case FIRE:
                    this.setIconId(FIRE_ICON);
                    break;
                case RESCUE:
                    this.setIconId(RESCUE_ICON);
                    break;
                case BLOCKED:
                    this.setIconId(BLOCKED_ICON);
                    break;
                case OTHERS:
                    this.setIconId(OTHERS_ICON);
                    break;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 地図上にプロット
    @Override
    public void plot(GoogleMap map) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String title = sdf.format(this.created_at);
        String description = this.comment;
        super.plot(map, title, description);
    }


}
