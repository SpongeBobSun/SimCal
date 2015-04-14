package sun.bob.simcal.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mDateData;
import sun.bob.simcal.model.mMonthData;


/**
 * Created by sunkuan on 15/3/23.
 */
public class mCalendarViewPager extends ViewPager {
    private int currentPageYear;
    private int currentPageMonth;

    public mCalendarViewPager(Context context) {
        super(context);
        this.setId(R.id.calendarViewPager);
        this.setAdapter(new ScreenSlidePagerAdapter(((ActionBarActivity) context).getSupportFragmentManager()));
        this.setCurrentItem(500);
        this.setYear(mMonthData.getCurrentYear());
        this.setMonth(mMonthData.getCurrentMonth());
//        this.setOnPageChangeListener(new CalendarScrollListener());
    }
    public mCalendarViewPager(Context context, AttributeSet attr){
        super(context);
        this.setId(R.id.calendarViewPager);
        this.setAdapter(new ScreenSlidePagerAdapter(((ActionBarActivity) context).getSupportFragmentManager()));
        this.setCurrentItem(500);
        this.setYear(mMonthData.getCurrentYear());
        this.setMonth(mMonthData.getCurrentMonth());
//        this.setOnPageChangeListener(new CalendarScrollListener());
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            int year = mMonthData.getCurrentYear();
            int month = mMonthData.getCurrentMonth();
            if(position > 500){
                year += (position - 500)/12;
                month += (position - 500)%12;
            }else{
                year -= (500 - position)/12;
                month -= (500 - position)%12;
            }
            mCalendarViewFragment fragment = new mCalendarViewFragment();
            mMonthData monthData = new mMonthData(getContext());
            monthData.changeMonth(year, month);
            fragment.setMonthData(monthData);
            return fragment;
        }
        @Override
        public int getCount() {
            return 1000;
        }
    }
    public int getYear(){return currentPageYear;}
    public int getMonth(){return currentPageMonth;}
    public void setYear(int year){this.currentPageYear = year;}
    public void setMonth(int month){this.currentPageMonth = month;}

    public static int position2Year(int pos){
        int tmpYear,tmpMonth;
        Calendar c = Calendar.getInstance();
        tmpYear = c.get(Calendar.YEAR);
        tmpMonth = mCalendarViewPager.position2Month(pos);
        int ret;
        if(pos == 500){
            return tmpYear;
        }
        if(pos > 500){

            ret = tmpYear + ((pos - 500) + c.get(Calendar.MONTH))/12;

        }else{
            ret =  tmpYear - ((500 - pos)+tmpMonth)/12;
        }
        return ret;
    }

    public static int position2Month(int pos){
        int tmpMonth;
        Calendar c = Calendar.getInstance();
        tmpMonth = c.get(Calendar.MONTH) + 1;
        int ret;
        if(pos == 500){
            return tmpMonth;
        }
        if(pos > 500){
            ret = (tmpMonth + (pos - 500)%12) % 12;
        }else{
            ret = (tmpMonth - (500 - pos)%12) % 12;
            ret = ret<0?12+ret:ret;
        }
        return ret==0?12:ret;
    }
    public mDateData getCurrentSelectDate(){
        int index = this.getCurrentItem();
        return ((mCalendarViewFragment)((ScreenSlidePagerAdapter)this.getAdapter()).getItem(index)).getCurrentSelectDate();
    }

}
