package com.app.myproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.myproject.R;
import com.app.myproject.interfces.ClickInterface;
import com.app.myproject.models.MyModel;

import java.util.ArrayList;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by minhaj nadeem on 12/07/2018.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<MyModel> mArrayList;
    private ClickInterface mClickInterface;

    public MyAdapter(Context context, ArrayList<MyModel> myModels, ClickInterface clickInterface){
        mContext = context;
        mArrayList = myModels;
        mClickInterface = clickInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        setSizeOfLayout(holder);
        MyModel myModel = mArrayList.get(position);

        holder.mTextView.setText(myModel.getNumber() + "");
        if (myModel.isSelected()){
            holder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.white));
        }else {
            holder.mTextView.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
            holder.mTextView.setTextColor(mContext.getResources().getColor(R.color.textColor));
        }
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickInterface.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    private void setSizeOfLayout(MyViewHolder holder) {
        WindowManager wm = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(displayMetrics);
        int h = displayMetrics.heightPixels;
        int w = displayMetrics.widthPixels;

        int noOfItemsPerRow = 3;
        int layoutWidth = w / noOfItemsPerRow;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) holder.mTextView.getLayoutParams();
        params.width = layoutWidth;
        params.height = layoutWidth;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView);
        }
    }
}
