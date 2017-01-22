package sakuraya.bousai_g.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import sakuraya.bousai_g.R;
import sakuraya.bousai_g.tasks.PostMarkerTask;

/**
 * Created by sakuraya on 2017/01/22.
 */

public class PostMarkerDialog extends DialogFragment {

    private boolean isChecked = false;
    private int checkedId = 0;

    private double latitude = 0;
    private double longitude = 0 ;

    public PostMarkerDialog() {
        super();
    }

    public void setLatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View content = inflater.inflate(R.layout.dialog_post_marker2, null);

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
                        if(latitude != 0) {
                            PostMarkerTask postMarkerTask = new PostMarkerTask(getActivity());
                            postMarkerTask.execute(
                                    Double.toString(latitude),
                                    Double.toString(longitude),
                                    comment,
                                    status
                            );
                        } else {

                            PostMarkerTask postMarkerTask = new PostMarkerTask(getActivity());
                            postMarkerTask.execute(
                                    Double.toString(((MapsActivity) getActivity()).getCurrentLatitude()),
                                    Double.toString(((MapsActivity) getActivity()).getCurrentLongitude()),
                                    comment,
                                    status
                            );
                        }
//                        PostMarkerTask postMarkerTask1 = new PostMarkerTask(getActivity());
//                        postMarkerTask1.execute(
//                                "35.682179",
//                                "139.738744",
//                                "足が折れた人がいます",
//                                "3"
//                        );
//                        PostMarkerTask postMarkerTask2 = new PostMarkerTask(getActivity());
//                        postMarkerTask2.execute(
//                                "35.68264",
//                                "139.738991",
//                                "足の不自由な高齢者がいます",
//                                "3"
//                        );
//                        PostMarkerTask postMarkerTask3 = new PostMarkerTask(getActivity());
//                        postMarkerTask3.execute(
//                                "35.677638",
//                                "139.733895",
//                                "ちょっと危ないです",
//                                "0"
//                        );
//                        PostMarkerTask postMarkerTask4 = new PostMarkerTask(getActivity());
//                        postMarkerTask4.execute(
//                                "35.678126",
//                                "139.734313",
//                                "なんか危ないです",
//                                "0"
//                        );


                    }
                });

        RadioButton radio0  = (RadioButton) content.findViewById(R.id.status_0);
        radio0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout wrapper = (LinearLayout) content.findViewById(R.id.status_0_wrapper);
                wrapper.setBackgroundColor(Color.parseColor("#ffcccc"));
                if(checkedId != 0) {
                    content.findViewById(checkedId).setBackgroundColor(Color.WHITE);
                }
                checkedId = R.id.status_0_wrapper;
            }
        });
        RadioButton radio1  = (RadioButton) content.findViewById(R.id.status_1);
        radio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout wrapper = (LinearLayout) content.findViewById(R.id.status_1_wrapper);
                wrapper.setBackgroundColor(Color.parseColor("#ffcccc"));
                if(checkedId != 0) {
                    content.findViewById(checkedId).setBackgroundColor(Color.WHITE);
                }
                checkedId = R.id.status_1_wrapper;
            }
        });

        RadioButton radio2  = (RadioButton) content.findViewById(R.id.status_2);
        radio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout wrapper = (LinearLayout) content.findViewById(R.id.status_2_wrapper);
                wrapper.setBackgroundColor(Color.parseColor("#ffcccc"));
                if(checkedId != 0) {
                    content.findViewById(checkedId).setBackgroundColor(Color.WHITE);
                }
                checkedId = R.id.status_2_wrapper;
            }
        });

        RadioButton radio3  = (RadioButton) content.findViewById(R.id.status_3);
        radio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout wrapper = (LinearLayout) content.findViewById(R.id.status_3_wrapper);
                wrapper.setBackgroundColor(Color.parseColor("#ffcccc"));
                if(checkedId != 0) {
                    content.findViewById(checkedId).setBackgroundColor(Color.WHITE);
                }
                checkedId = R.id.status_3_wrapper;
            }
        });

        RadioButton radio4  = (RadioButton) content.findViewById(R.id.status_4);
        radio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout wrapper = (LinearLayout) content.findViewById(R.id.status_4_wrapper);
                wrapper.setBackgroundColor(Color.parseColor("#ffcccc"));
                if(checkedId != 0) {
                    content.findViewById(checkedId).setBackgroundColor(Color.WHITE);
                }
                checkedId = R.id.status_4_wrapper;
            }
        });

        RadioButton radio5  = (RadioButton) content.findViewById(R.id.status_5);
        radio5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout wrapper = (LinearLayout) content.findViewById(R.id.status_5_wrapper);
                wrapper.setBackgroundColor(Color.parseColor("#ffcccc"));
                if(checkedId != 0) {
                    content.findViewById(checkedId).setBackgroundColor(Color.WHITE);
                }
                checkedId = R.id.status_5_wrapper;
            }
        });



        // Create the AlertDialog object and return it
        return builder.create();
    }
}