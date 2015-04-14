package sun.bob.simcal.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mEventBean;

/**
 * Created by sunkuan on 15/4/6.
 */
public class TodayEventAdapter extends ArrayAdapter {
    private ArrayList<mEventBean> eventBeans;
    public TodayEventAdapter(Context context, int resource, ArrayList objects) {
        super(context, resource, objects);
        eventBeans = objects;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View retView = convertView;
        ViewHolder holder;
        mEventBean bean = (mEventBean) getItem(position);
        if(retView == null) {
            retView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.today_event_item, null);
            holder = new ViewHolder();
            retView.setTag(holder);
//            holder.startTimeTextView = retView.findViewById();
//            holder.endTimeTextView = retView.findViewById();
        }else{
            holder = (ViewHolder)retView.getTag();
        }
        Toast.makeText(getContext(),""+bean.getYear()+bean.getMonth()+bean.getDay()+bean.getTitle(),Toast.LENGTH_LONG).show();

        return retView;
    }
    class ViewHolder{
        public TextView startTimeTextView;
        public TextView endTimeTextView;
    }
}
