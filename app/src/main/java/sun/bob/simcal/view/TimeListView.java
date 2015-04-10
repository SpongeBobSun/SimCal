package sun.bob.simcal.view;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mEventBean;
import sun.bob.simcal.model.mMonthData;
import sun.bob.simcal.persistence.EventSQLUtils;

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

public class TimeListView extends LinearLayout {
    private ArrayList<mEventBean> eventsOfDay;
    private mMonthData monthData;
    private int currentCCYY;
    private int currentMM;
    private int currentDD;

    public TimeListView(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        eventsOfDay = new ArrayList<>();
        View view;
        for(int i = 0; i < 24; i++){
            view = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.time_row,null);
            TextView timeText = (TextView)view.findViewById(R.id.id_time_text);
            timeText.setText(String.format("%d:00",i));
            this.addView(view);
        }

//        currentCCYY = mMonthData.getCurrentYear();
//        currentMM = mMonthData.getCurrentMonth();
//        currentDD = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+1;
        registerBroadcastReceiver();

    }

    public void addEvent(mEventBean eventBean){

    }
    public mEventBean getEvent(){
        mEventBean ret = null;
        return  ret;
    }
    public void delEvent(){

    }
    private void loadFromSQL(){
        eventsOfDay =  EventSQLUtils.getStaticInstance(this.getContext()).getDayEvent(currentCCYY,currentMM,currentDD);
        Iterator i = eventsOfDay.iterator();
        mEventBean eventBean;
        LinearLayout linearLayout;
        LinearLayout addItem;
        int indexhour;
        View eventView;
        boolean addFlag = true;
        clearEvnets();
        while(i.hasNext()){
            eventBean = (mEventBean) i.next();
            indexhour = eventBean.getHour();
            linearLayout = (LinearLayout) this.getChildAt(indexhour).findViewById(R.id.id_event_container);
            for(int count = 0; count < linearLayout.getChildCount();count++){
                eventView = linearLayout.getChildAt(count);
                if((eventView.getTag()).equals(String.valueOf(eventBean.get_id()))){
                    addFlag = false;
                    break;
                }
                addFlag = true;
            }
            if(!addFlag){
                continue;
            }
            /**
             * Notice:
             *          Must pass a parent layout to the inflater.
             *          Because we are using a ScrollView as a container of this LinearLayout.
             *          If we DO NOT pass a parent view, this view will NOT be displayed at all.
             */
            addItem = (LinearLayout) ((Activity)getContext()).getLayoutInflater().inflate(R.layout.event_view,linearLayout,false);
            TextView titile = (TextView) addItem.findViewById(R.id.id_text_view_event_title);
            titile.setText(eventBean.getTitle());
            TextView time = (TextView) addItem.findViewById(R.id.id_text_view_event_time);
            time.setText(String.format("%d:%d", eventBean.getHour(), eventBean.getMinute()));
            linearLayout.addView(addItem);
            addItem.setTag(String.valueOf(eventBean.get_id()));
        }

    }
    public void loadEvent(int year, int month, int day){
        currentCCYY = year;
        currentMM = month;
        currentDD = day;
        loadFromSQL();
    }
    public void setMonthData(mMonthData data){
        currentCCYY = data.getYear();
        currentMM = data.getMonth();
        currentDD = data.getCenterDay();
    }

    private void registerBroadcastReceiver(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("intent_date_cell_click");
        getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equals("intent_date_cell_click")){
                    currentCCYY = intent.getIntExtra("CCYY",-1);
                    currentMM = intent.getIntExtra("MM",-1);
                    currentDD = intent.getIntExtra("DD",-1);
                    loadFromSQL();
                }
            }
        }
        ,intentFilter);
    }
    private void clearEvnets(){
        LinearLayout linearLayout;
        for(int i = 0; i < 23; i++){
            linearLayout = (LinearLayout) this.getChildAt(i).findViewById(R.id.id_event_container);
            linearLayout.removeAllViews();
        }
    }
    class EventOnClickListener implements OnClickListener{

        @Override
        public void onClick(View view) {
            int eventId = Integer.getInteger((String)view.getTag());
            EventSQLUtils.getStaticInstance(getContext()).getEventById(eventId);
        }
    }
}
