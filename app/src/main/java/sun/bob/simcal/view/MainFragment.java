package sun.bob.simcal.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sun.bob.simcal.R;

/**
 * Created by sunkuan on 15/3/30.
 */
public class MainFragment extends Fragment {
    private mCalendarViewPager calendarViewPager;
    View title;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
//        LinearLayout retView = (LinearLayout) inflater.inflate(R.layout.fragment_main,parent,false);
        LinearLayout retView = new LinearLayout(getActivity());
        retView.setOrientation(LinearLayout.VERTICAL);
        title = LayoutInflater.from(getActivity()).inflate(R.layout.calendar_title,null);
        calendarViewPager = new mCalendarViewPager(getActivity());

        TextView year = (TextView)title.findViewById(R.id.id_textview_title_year);
        year.setText(String.valueOf(calendarViewPager.getYear()));
        TextView month = (TextView)title.findViewById(R.id.id_textview_title_month);
        month.setText(String.valueOf(calendarViewPager.getMonth()));

        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.WRAP_CONTENT);
        retView.addView(title,0,titleParams);

        LinearLayout.LayoutParams calendarParms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                , LinearLayout.LayoutParams.MATCH_PARENT);
        retView.addView(calendarViewPager,1,calendarParms);

        calendarViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                calendarViewPager.setYear(mCalendarViewPager.position2Year(position));
                calendarViewPager.setMonth(mCalendarViewPager.position2Month(position));
                TextView year = (TextView)title.findViewById(R.id.id_textview_title_year);
                year.setText(String.valueOf(calendarViewPager.getYear()));
                TextView month = (TextView)title.findViewById(R.id.id_textview_title_month);
                month.setText(String.valueOf(calendarViewPager.getMonth()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return retView;
    }
}
