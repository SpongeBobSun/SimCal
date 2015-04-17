package sun.bob.simcal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sun.bob.simcal.R;

/**
 * Created by sunkuan on 15/4/16.
 */
public class SettingFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        View ret = inflater.inflate(R.layout.settings_activity,parent,false);
        return ret;
    }
}
