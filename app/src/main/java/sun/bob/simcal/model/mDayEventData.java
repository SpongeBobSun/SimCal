package sun.bob.simcal.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.simcal.persistence.EventSQLUtils;

/**
 * Created by sunkuan on 15/4/13.
 */
public class mDayEventData {
    private ArrayList<mEventBean> content;
    private static mDayEventData staticInstance;
    private mDayEventData(Context context){
        Calendar calendar = Calendar.getInstance();
        content = EventSQLUtils.getStaticInstance(context).getDayEvent(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
    }

    public static mDayEventData getStaticInstance(Context context) {
        if(staticInstance == null){
            staticInstance = new mDayEventData(context);
        }
        return staticInstance;
    }

    public ArrayList<mEventBean> getContent() {
        return content;
    }
}
