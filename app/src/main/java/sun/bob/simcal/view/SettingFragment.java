package sun.bob.simcal.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import sun.bob.simcal.AboutActivity;
import sun.bob.simcal.R;
import sun.bob.simcal.persistence.PreferenceUtils;

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
        setupLayout(ret);
        setupListeners(ret);
        return ret;
    }
    private void setupLayout(View view){
        ((CheckBox) view.findViewById(R.id.id_check_box_sound))
            .setChecked(PreferenceUtils.getInstance(getActivity()).isSound());
        ((CheckBox) view.findViewById(R.id.id_check_box_vibrate))
            .setChecked(PreferenceUtils.getInstance(getActivity()).isVibrate());
        ((TextView) view.findViewById(R.id.id_text_view_later_minute))
            .setText(String.valueOf(PreferenceUtils.getInstance(getActivity()).getDelayMin()));

    }
    private void setupListeners(View view){
        ((CheckBox) view.findViewById(R.id.id_check_box_sound))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        PreferenceUtils.getInstance(getActivity()).setSound(isChecked);
                    }
                });
        ((CheckBox) view.findViewById(R.id.id_check_box_vibrate))
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        PreferenceUtils.getInstance(getActivity()).setVibrate(isChecked);
                    }
                });
        ((TextView) view.findViewById(R.id.id_text_view_about_button))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), AboutActivity.class);
                        getActivity().startActivity(intent);
                    }
                });
    }
}
