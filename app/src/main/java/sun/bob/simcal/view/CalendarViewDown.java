package sun.bob.simcal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import java.util.ArrayList;

import sun.bob.simcal.controller.mCalendarAdapter;
import sun.bob.simcal.model.mMonthData;

/**
 * Created by sunkuan on 15/3/30.
 */
public class CalendarViewDown extends GridView {
    private mMonthData monthData;
    private mCalendarAdapter adapter;
    public CalendarViewDown(Context context) {
        super(context);
    }
    public void setMonthData(mMonthData monthData){
        this.monthData = monthData;
        adapter = new mCalendarAdapter(getContext(),android.R.layout.simple_list_item_1,monthData.getArrayDown(monthData.getCenterDay()));
        this.setAdapter(adapter);
        this.setNumColumns(7);
    }

    public CalendarViewDown(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void markDay(int day, int markColor, int markStyle){
        monthData.markDay(day,getResources().getColor(markColor),markStyle);
        adapter.notifyDataSetChanged();
    }

    public mCalendarAdapter getCalendarAdapter(){
        return adapter;
    }
    public mMonthData getMonthData(){return monthData;}
}
