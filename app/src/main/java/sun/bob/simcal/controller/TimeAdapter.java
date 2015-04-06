package sun.bob.simcal.controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sun.bob.simcal.R;

/**
 * Created by sunkuan on 15/4/6.
 */
public class TimeAdapter extends ArrayAdapter {
    Context appContext;
    public TimeAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        appContext = context;
    }
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View retView;
        retView = ((Activity)appContext).getLayoutInflater().inflate(R.layout.time_row,null);
        TextView timeText = (TextView)retView.findViewById(R.id.id_time_text);
        timeText.setText(String.format("%d:00",position));
        return retView;
    }
}
