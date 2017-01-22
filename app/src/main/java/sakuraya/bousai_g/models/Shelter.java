package sakuraya.bousai_g.models;

/**
 * Created by sakuraya on 2017/01/22.
 */

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

import sakuraya.bousai_g.R;

/**
 * 避難所
 */
public class Shelter extends BaseMarker {

    private static final int iconId = R.drawable.shelter_m;

    // 避難所名
    private String name;
    // 避難所種別
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Shelter(JSONObject json) {
        super(json);
        try {
            this.name = json.getString("name");
            this.type = json.getString("type");
            super.setIconId(iconId);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 地図上にプロット
    @Override
    public void plot(GoogleMap map) {
        String title = "避難所名：" + this.name;
        String description =  "種別:" + "：" + this.type;
        super.plot(map, title, description);
    }

}
