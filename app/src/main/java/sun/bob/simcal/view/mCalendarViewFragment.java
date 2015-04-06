package sun.bob.simcal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import sun.bob.simcal.controller.mCalendarAdapter;
import sun.bob.simcal.model.mMonthData;

/**
 * Created by sunkuan on 15/3/23.
 */
public class mCalendarViewFragment extends Fragment {

    private CalendarViewUp calendarViewUp;
    private CalendarViewDown calendarViewDown;

    private mMonthData monthData;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,Bundle savedInstanceState){
        LinearLayout retView = new LinearLayout(getActivity());
        retView.setOrientation(LinearLayout.VERTICAL);
        calendarViewUp = new CalendarViewUp(getActivity());
        calendarViewDown = new CalendarViewDown(getActivity());
        calendarViewUp.setMonthData(monthData);
        calendarViewDown.setMonthData(monthData);
        LinearLayout.LayoutParams paramsUp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);
        retView.addView(calendarViewUp,paramsUp);
        LinearLayout.LayoutParams paramsDown = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);
        retView.addView(calendarViewDown,paramsDown);
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
                int day = upMonthData.getArray().get(position + 7).getDay();
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

                int day = downMonthData.getArray().get(position).getDay();
                mCalendarAdapter adapterUp = new mCalendarAdapter(getActivity(),android.R.layout.simple_list_item_1,monthData.getArrayUp(day));
                calendarViewUp.setAdapter(adapterUp);
                mCalendarAdapter adapterDown = new mCalendarAdapter(getActivity(),android.R.layout.simple_list_item_1,monthData.getArrayDown(day));
                calendarViewDown.setAdapter(adapterDown);
            }
        });
    }
}
