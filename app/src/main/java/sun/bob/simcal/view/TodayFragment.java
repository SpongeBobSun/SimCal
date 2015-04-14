package sun.bob.simcal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import sun.bob.simcal.R;
import sun.bob.simcal.controller.TodayEventAdapter;
import sun.bob.simcal.model.mDayEventData;
import sun.bob.simcal.model.mMonthData;

/**
 * Created by sunkuan on 15/4/12.
 */
public class TodayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        View ret;
        ret = inflater.inflate(R.layout.layout_today_fragment,parent,false);
        TextView title = (TextView) ret.findViewById(R.id.id_text_view_list_title);
        title.setText(mMonthData.getCurrentCCYY()+"-"+mMonthData.getCurrentMM()+"-"+mMonthData.getCurrentDD());
        ListView listView = (ListView) ret.findViewById(R.id.id_today_fragment_list);
        listView.setDivider(null);
        listView.setEmptyView(getActivity().getLayoutInflater().inflate(R.layout.today_fragment_empty_view,null));
        TodayEventAdapter adapter = new TodayEventAdapter(getActivity(),android.R.layout.simple_list_item_1, mDayEventData.getStaticInstance(getActivity()).getContent());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return ret;
    }
}
