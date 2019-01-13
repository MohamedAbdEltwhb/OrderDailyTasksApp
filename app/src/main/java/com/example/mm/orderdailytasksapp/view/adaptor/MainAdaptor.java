package com.example.mm.orderdailytasksapp.view.adaptor;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mm.orderdailytasksapp.R;

import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.DATE;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.DETAIL;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.TIME;
import static com.example.mm.orderdailytasksapp.models.SQLiteHelper.Constants.TITLE;

public class MainAdaptor extends RecyclerView.Adapter<MainAdaptor.MainViewHolder>{

    private Context mContext;
    private Cursor mCursor;

    public MainAdaptor(Context mContext, Cursor mCursor) {
        this.mContext = mContext;
        this.mCursor = mCursor;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_entry, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)){
            return;
        }
        String title = mCursor.getString(mCursor.getColumnIndex(TITLE));
        String mDetail = mCursor.getString(mCursor.getColumnIndex(DETAIL));
        String time = mCursor.getString(mCursor.getColumnIndex(TIME));
        String date = mCursor.getString(mCursor.getColumnIndex(DATE));

        holder.title.setText(title);
        holder.mDetail.setText(mDetail);
        holder.time.setText(time);
        holder.date.setText(date);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }


    public class MainViewHolder extends RecyclerView.ViewHolder{

        TextView title, mDetail, time, date;

        public MainViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            mDetail = itemView.findViewById(R.id.Detail);
            time = itemView.findViewById(R.id.time);
            date = itemView.findViewById(R.id.date);
        }
    }
}
