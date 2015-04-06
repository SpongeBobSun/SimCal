package sun.bob.simcal.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.widget.ListView;

import sun.bob.simcal.controller.TimeAdapter;
import sun.bob.simcal.model.mTimeData;

/**
 * Created by sunkuan on 15/4/6.
 */
public class TimeListView extends ListView {
    public TimeListView(Context context) {
        super(context);
        mTimeData timeData = new mTimeData(context);
        TimeAdapter adpter = new TimeAdapter(context, android.R.layout.simple_list_item_1,timeData.getContent());
        this.setAdapter(adpter);
        this.setDividerHeight(0);
//        initHeight();
    }

    public void initHeight(){
        DisplayMetrics dm;
        dm = getContext().getResources().getDisplayMetrics();
        this.getLayoutParams().height = 3*((dm.widthPixels - 10)/7);
//        this.setMinimumHeight(dm.widthPixels-10 / 7);
    }
}
