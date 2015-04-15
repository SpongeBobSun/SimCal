package sun.bob.simcal.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.simcal.persistence.EventSQLUtils;

/**
 * Created by sunkuan on 15/4/13.
 */
public class mDayEventData {
    private Context appContext;
    private ArrayList<mEventBean> content;
    private Calendar calendar;
    public mDayEventData(Context context){
        calendar = Calendar.getInstance();
        appContext = context;
        content = EventSQLUtils.getStaticInstance(context).getDayEvent(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
    }

    public ArrayList<mEventBean> refreshContent(){
        content.clear();
        content = EventSQLUtils.getStaticInstance(appContext).getDayEvent(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        return content;
    }

    public ArrayList<mEventBean> getContent() {
        return content;
    }
}
