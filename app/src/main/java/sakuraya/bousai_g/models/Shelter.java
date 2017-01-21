package sakuraya.bousai_g.models;

/**
 * Created by sakuraya on 2017/01/22.
 */

import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

/**
 * 避難所
 */
public class Shelter extends BaseMarker {

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
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // 地図上にプロット
    @Override
    public void plot(GoogleMap map) {
        String description = "避難所名：" + this.name + "¥n種別:" + "：" + this.type;
        super.plot(map, description);
    }

}
