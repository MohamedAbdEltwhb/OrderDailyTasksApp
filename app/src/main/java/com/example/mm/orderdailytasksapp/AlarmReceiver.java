package com.example.mm.orderdailytasksapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mm.orderdailytasksapp.view.activities.Alert;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String Title = intent.getStringExtra(context.getString(R.string.titttle));

        Intent intent1 = new Intent(context, Alert.class);

        intent1.putExtra(context.getString(R.string.titttle), Title);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
