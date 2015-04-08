package sun.bob.simcal.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mMonthData;

/**
 * Created by sunkuan on 2015/4/8.
 */
public class AddEventFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup father,Bundle savedInstanceState){
        View ret = null;
        ret = inflater.inflate(R.layout.layout_add_event,father,false);
        TextView startDateTextView = (TextView) ret.findViewById(R.id.id_date_picker_start);
        TextView startTimeTextView = (TextView) ret.findViewById(R.id.id_time_picker_start);
        TextView endDateTextView = (TextView) ret.findViewById(R.id.id_date_picker_end);
        TextView endTimeTextView = (TextView) ret.findViewById(R.id.id_time_picker_end);

        startDateTextView.setText(mMonthData.getCurrentCCYY()+"-"+mMonthData.getCurrentMMDD());
        startTimeTextView.setText(mMonthData.getCurrentHHMM());

        endDateTextView.setText(mMonthData.getCurrentCCYY()+"-"+mMonthData.getCurrentMMDD());
        endTimeTextView.setText(mMonthData.getDefaultEndHHMM());

        initListener(ret);

        return ret;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_event) {

            return true;
        }
        return false;
    }
    public void initListener(View v){
        LinearLayout startContainer = (LinearLayout) v.findViewById(R.id.id_start_selector_container);
        startContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo
                //Change text color here.
                Intent intent = new Intent(getActivity(),DatePickerActivity.class);
                getActivity().startActivityForResult(intent, 0);
            }
        });
        LinearLayout endContainer = (LinearLayout) v.findViewById(R.id.id_end_selector_container);
        endContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DatePickerActivity.class);
                getActivity().startActivityForResult(intent, 1);
            }
        });
    }
}
