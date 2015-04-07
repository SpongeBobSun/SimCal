package sun.bob.simcal.controller;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mDateData;
import sun.bob.simcal.model.mMonthData;
import sun.bob.simcal.model.mTitleData;

/**
 * Created by sunkuan on 15/3/17.
 */
public class mCalendarAdapter extends ArrayAdapter<mDateData> {
    ArrayList<mDateData> dateDatas;
    private int cellSize;
    LinearLayout.LayoutParams cellParams;
    public mCalendarAdapter(Context context, int resource, ArrayList<mDateData> arrayList) {
        super(context, resource, arrayList);
        dateDatas = arrayList;
        getDefaultcellSize();
        cellParams = new LinearLayout.LayoutParams(cellSize,cellSize);
    }
    private void getDefaultcellSize(){
        DisplayMetrics dm;
        dm = getContext().getResources().getDisplayMetrics();
        cellSize = (dm.widthPixels-10) / 7;
    }

    @Override
    public int getCount(){
        return dateDatas.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        ArrayList<mDateData> list = mMonthData.getInstance(getContext()).getArray();_
        ArrayList<mDateData> list = dateDatas;
        View retView;
        retView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.date_cell,null);
        TextView textView = (TextView)retView.findViewById(R.id.id_date_cell_textview);
        mDateData dateData = list.get(position);
//        if(position < 7){
//            textView.setText(((mTitleData)dateData).getTitle());
//            textView.setTextSize(cellSize / 4);
//            textView.setBackgroundColor(getContext().getResources().getColor(R.color.lightblue));
//            textView.setWidth(cellSize);
//            LinearLayout linearLayout = (LinearLayout)retView.findViewById(R.id.id_date_cell_text_container);
//            linearLayout.removeViewAt(1);
//            return retView;
//        }
        textView.setWidth(cellSize);
        textView.setHeight(cellSize);
        if(dateData.isBlank()){
            retView.setClickable(false);
            textView.setText("");
            retView.setBackgroundResource(R.drawable.empty_cell_border);
            return retView;
        }
        if(dateData.getDay() == 0) {
            textView.setText("");
        }else{
            textView.setText(String.format("%d",dateData.getDay()));
        }
        if(dateData.getTextSize() == 1) {
            textView.setTextSize(cellSize / 3);
        }
        if(dateData.getTextSize() == 0){
            textView.setTextSize(cellSize / 5);
            textView.setLayoutParams(cellParams);
        }
        textView.setTextColor(dateData.getTextColor());
        if(dateData.isMarked()){
            View view = retView.findViewById(R.id.id_date_cell_mark_bar);
            view.setBackgroundColor(dateData.getMarkColor());
        }
        return retView;
    }

    public int getCellSize() {
        return cellSize;
    }

    public mDateData getLastValidDate() {
        mDateData ret;
        for(int i = dateDatas.size() - 1; i >= 0; i--){
            ret = dateDatas.get(i);
            if(ret.getDay() != 0){
                return ret;
            }
        }
        return dateDatas.get(dateDatas.size());
    }
}
