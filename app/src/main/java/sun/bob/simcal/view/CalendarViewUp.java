package sun.bob.simcal.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.GridView;

import java.util.ArrayList;

import sun.bob.simcal.controller.mCalendarAdapter;
import sun.bob.simcal.model.mDateData;
import sun.bob.simcal.model.mEventBean;
import sun.bob.simcal.model.mMonthData;
import sun.bob.simcal.persistence.EventSQLUtils;

/**
 * Created by sunkuan on 15/3/30.
 */
public class CalendarViewUp extends GridView {
    private mMonthData monthData;
    private mCalendarAdapter adapter;
    public CalendarViewUp(Context context) {
        super(context);
    }
    public void setMonthData(mMonthData monthData){
        this.monthData = monthData;
        ArrayList<mEventBean> eventDays = EventSQLUtils.getStaticInstance(getContext()).getMonthEvent(monthData.getYear(),monthData.getMonth()+1);
        adapter = new mCalendarAdapter(getContext(),android.R.layout.simple_list_item_1,monthData.getArrayUp(monthData.getCenterDay()));
        for(int i = 0; i < eventDays.size();i++){
            markDay(eventDays.get(i).getDay(),getResources().getColor(android.support.v7.appcompat.R.color.accent_material_light),0);
        }
        this.setAdapter(adapter);
        this.setNumColumns(7);
    }

    public CalendarViewUp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    private void markDay(int day, int markColor, int markStyle){
        monthData.markDay(day, Color.BLUE,markStyle);
    }

    public mCalendarAdapter getCalendarAdapter(){
        return adapter;
    }
    public mMonthData getMonthData(){return monthData;}
    public mDateData getCurrentSelectDate(){
        return ((mCalendarAdapter)getAdapter()).getLastValidDate();
    }
}
