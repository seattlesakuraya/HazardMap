package sakuraya.bousai_g.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import sakuraya.bousai_g.R;

/**
 * Created by sakuraya on 2017/01/22.
 */

public class PostMarkerDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View content = inflater.inflate(R.layout.dialog_post_marker, null);

        builder.setView(content);
        builder.setMessage("投稿")
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .setPositiveButton("登録", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // ステータス
                        RadioGroup statusRadioGroup = (RadioGroup)(content.findViewById(R.id.status));
                        int statusId = statusRadioGroup.getCheckedRadioButtonId();
                        String status = "";
                        switch (statusId) {
                            case R.id.status_0:
                                status = "0";
                                break;
                            case R.id.status_1:
                                status = "1";
                                break;
                            case R.id.status_2:
                                status = "2";
                                break;
                            case R.id.status_3:
                                status = "3";
                                break;
                            case R.id.status_4:
                                status = "4";
                                break;
                            case R.id.status_5:
                                status = "5";
                                break;
                        }
                        // コメント
                        String comment = ((EditText)content.findViewById(R.id.comment)).getText().toString();

//                        Log.d("bousai_g", status);
//                        Log.d("bousai_g", comment);

//                        PostMarkerTask postMarkerTask = new PostMarkerTask();
//                        postMarkerTask.execute(
//                                Double.toString(((MapsActivity)getActivity()).getCurrentLatitude()),
//                                Double.toString(((MapsActivity)getActivity()).getCurrentLongitude()),
//                                comment,
//                                status
//                        );
//                        PostMarkerTask postMarkerTask = new PostMarkerTask();
//                        postMarkerTask.execute(
//                                "35.67553",
//                                "139.732732",
//                                "道路が陥没しています",
//                                "0"
//                        );
//                        PostMarkerTask postMarkerTask1 = new PostMarkerTask();
//                        postMarkerTask1.execute(
//                                "35.67471",
//                                "139.739556",
//                                "ビルが倒壊しています",
//                                "1"
//                        );
//                        PostMarkerTask postMarkerTask2 = new PostMarkerTask();
//                        postMarkerTask2.execute(
//                                "35.679204",
//                                "139.738933",
//                                "燃えてます",
//                                "2"
//                        );
//                        PostMarkerTask postMarkerTask3 = new PostMarkerTask();
//                        postMarkerTask3.execute(
//                                "35.677368",
//                                "139.737455",
//                                "瓦礫の下敷きになっている人がいます",
//                                "3"
//                        );
//                        PostMarkerTask postMarkerTask4 = new PostMarkerTask();
//                        postMarkerTask4.execute(
//                                "35.674624",
//                                "139.735403",
//                                "通れません",
//                                "4"
//                        );
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}