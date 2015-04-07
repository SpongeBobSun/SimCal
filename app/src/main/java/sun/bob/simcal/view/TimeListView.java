package sun.bob.simcal.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mEventBean;

/**
 * Created by sunkuan on 15/4/6.
 */
//public class TimeListView extends ListView {
//    public TimeListView(Context context) {
//        super(context);
//        mTimeData timeData = new mTimeData(context);
//        TimeAdapter adpter = new TimeAdapter(context, android.R.layout.simple_list_item_1,timeData.getContent());
//        this.setAdapter(adpter);
//        this.setDividerHeight(0);
////        initHeight();
//    }
//
//    public void initHeight(){
//        DisplayMetrics dm;
//        dm = getContext().getResources().getDisplayMetrics();
//        this.getLayoutParams().height = 3*((dm.widthPixels - 10)/7);
////        this.setMinimumHeight(dm.widthPixels-10 / 7);
//    }
//}

public class TimeListView extends LinearLayout{
    Activity appContext;
    ArrayList<mEventBean> eventsOfDay;
    public TimeListView(Context context) {
        super(context);
        appContext = (Activity) context;
        this.setOrientation(LinearLayout.VERTICAL);
        eventsOfDay = new ArrayList<>();
        View view;
        for(int i = 0; i < 24; i++){
            view = appContext.getLayoutInflater().inflate(R.layout.time_row,null);
            TextView timeText = (TextView)view.findViewById(R.id.id_time_text);
            timeText.setText(String.format("%d:00",i));
            this.addView(view);
        }
    }

    public void addEvent(mEventBean eventBean){

    }
    public mEventBean getEvent(){
        mEventBean ret = null;
        return  ret;
    }
    public void delEvent(){

    }
}
