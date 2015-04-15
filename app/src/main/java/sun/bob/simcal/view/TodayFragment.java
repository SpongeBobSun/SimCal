package sun.bob.simcal.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import sun.bob.simcal.EditEventActivity;
import sun.bob.simcal.R;
import sun.bob.simcal.controller.TodayEventAdapter;
import sun.bob.simcal.model.mDayEventData;
import sun.bob.simcal.model.mEventBean;
import sun.bob.simcal.model.mMonthData;
import sun.bob.simcal.persistence.EventSQLUtils;

/**
 * Created by sunkuan on 15/4/12.
 */
public class TodayFragment extends Fragment {
    TodayEventAdapter adapter;
    ListView listView;
    mDayEventData dayEventData;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        View ret;
        ret = inflater.inflate(R.layout.layout_today_fragment,parent,false);
//        TextView title = (TextView) ret.findViewById(R.id.id_text_view_list_title);
//        title.setText(mMonthData.getCurrentCCYY()+"-"+mMonthData.getCurrentMM()+"-"+mMonthData.getCurrentDD());
        listView = (ListView) ret.findViewById(R.id.id_today_fragment_list);
        listView.setDivider(null);
        View emptyView = getActivity().getLayoutInflater().inflate(R.layout.today_fragment_empty_view,null);
        ((ViewGroup)listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        dayEventData = new mDayEventData(getActivity());
        adapter = new TodayEventAdapter(getActivity(),android.R.layout.simple_list_item_1,dayEventData.getContent());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int eventId = ((mEventBean)listView.getAdapter().getItem(position)).get_id();
                mEventBean eventBean = EventSQLUtils.getStaticInstance(getActivity()).getEventById(eventId);
                Intent intent = new Intent(getActivity(), EditEventActivity.class);
                intent.putExtra("eventID",eventId);
                intent.putExtra("startCCYY",eventBean.getYear());
                intent.putExtra("startMM",eventBean.getMonth());
                intent.putExtra("startDD",eventBean.getDay());
                intent.putExtra("startHH",eventBean.getHour());
                intent.putExtra("startTT",eventBean.getMinute());
                intent.putExtra("title",eventBean.getTitle());
                if(eventBean.getDetail() != null){
                    intent.putExtra("detail",eventBean.getDetail());
                }
                //Todo
                //This result will be handled in MainActivity

                getActivity().startActivityForResult(intent,0);
            }
        });
        return ret;
    }
    public void notifyDataSetChanged(){
        adapter = new TodayEventAdapter(getActivity(),android.R.layout.simple_list_item_1, dayEventData.refreshContent());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
