package sun.bob.simcal.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sun.bob.simcal.R;

/**
 * Created by sunkuan on 15/4/12.
 */
public class TodoFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup parent,Bundle savedInstanceState){
        View ret;
        ret = inflater.inflate(R.layout.layout_todo_fragment,parent,false);
        return ret;
    }
}
