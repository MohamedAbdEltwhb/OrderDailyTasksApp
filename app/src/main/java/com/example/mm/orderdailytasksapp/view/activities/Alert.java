package com.example.mm.orderdailytasksapp.view.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.mm.orderdailytasksapp.R;

public class Alert extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        mMediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.chec);
        mMediaPlayer.start();

        Intent intent = getIntent();

        String msg = getString(R.string.alarmtext);
        String title = intent.getExtras().getString(getString(R.string.title_msg));

        String dialogTitle = msg + title;

        showCustomDialog(dialogTitle);

    }

    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
    }

    public void showCustomDialog(final String dialogTitle) {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.castom_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final TextView textView = dialog.findViewById(R.id.text_title);
        final Button alertTitle =  dialog.findViewById(R.id.ok);

        alertTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(dialogTitle);
                dialog.dismiss();
                Alert.this.finish();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
