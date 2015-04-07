package sun.bob.simcal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import sun.bob.simcal.R;
import sun.bob.simcal.controller.mCalendarAdapter;
import sun.bob.simcal.model.mDateData;
import sun.bob.simcal.model.mMonthData;

/**
 * Created by sunkuan on 15/3/23.
 */
public class mCalendarViewFragment extends Fragment {

    private CalendarViewUp calendarViewUp;
    private CalendarViewDown calendarViewDown;
    private TimeListView timeListView;
    private ScrollView timeContainer;
    private mMonthData monthData;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
        LinearLayout retView = new LinearLayout(getActivity());
        retView.setPadding(0, 0, 0, 0);
        retView.setWeightSum(1.0f);
        retView.setOrientation(LinearLayout.VERTICAL);
        retView.setBackgroundResource(R.drawable.date_cell_border);

        calendarViewUp = new CalendarViewUp(getActivity());
        calendarViewUp.setPadding(0,0,0,0);
        calendarViewDown = new CalendarViewDown(getActivity());
        calendarViewDown.setPadding(0,0,0,0);

        timeContainer = new ScrollView(getActivity());
        timeListView = new TimeListView(getActivity());
        timeListView.setPadding(0,0,0,0);
        timeContainer.addView(timeListView);


        calendarViewUp.setMonthData(monthData);
        calendarViewDown.setMonthData(monthData);
        LinearLayout.LayoutParams paramsUp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        retView.addView(calendarViewUp,0,paramsUp);
        LinearLayout.LayoutParams paramsMiddle = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,1f);
        retView.addView(timeContainer,1,paramsMiddle);
        LinearLayout.LayoutParams paramsDown = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        retView.addView(calendarViewDown,2,paramsDown);
        calendarViewUp.setSelector(R.drawable.selector_null);
        calendarViewDown.setSelector(R.drawable.selector_null);
        initListeners();
        return retView;
    }
    public void setMonthData(mMonthData m){
        this.monthData = m;
    }
    private void initListeners(){
        calendarViewUp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMonthData upMonthData = ((CalendarViewUp)parent).getMonthData();
                mDateData dateData = upMonthData.getArray().get(position + 7);
                if(dateData.getMonth() != upMonthData.getMonth()){
                    return;
                }
                int day = dateData.getDay();

                mCalendarAdapter adapterUp = new mCalendarAdapter(getActivity(),android.R.layout.simple_list_item_1,monthData.getArrayUp(day));
                calendarViewUp.setAdapter(adapterUp);
                mCalendarAdapter adapterDown = new mCalendarAdapter(getActivity(),android.R.layout.simple_list_item_1,monthData.getArrayDown(day));
                calendarViewDown.setAdapter(adapterDown);
            }
        });
        calendarViewDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMonthData downMonthData = ((CalendarViewDown)parent).getMonthData();
                position += calendarViewUp.getCount();
                mDateData dateData = downMonthData.getArray().get(position);
                if(dateData.getMonth() != downMonthData.getMonth()){
                    return;
                }
                int day = dateData.getDay();
                mCalendarAdapter adapterUp = new mCalendarAdapter(getActivity(),android.R.layout.simple_list_item_1,monthData.getArrayUp(day));
                calendarViewUp.setAdapter(adapterUp);
                mCalendarAdapter adapterDown = new mCalendarAdapter(getActivity(),android.R.layout.simple_list_item_1,monthData.getArrayDown(day));
                calendarViewDown.setAdapter(adapterDown);
            }
        });
    }
    public mDateData getCurrentSelectDate(){
        return calendarViewUp.getCurrentSelectDate();
    }
}
