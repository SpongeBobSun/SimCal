package sun.bob.simcal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

import sun.bob.simcal.model.mMonthData;
import sun.bob.simcal.view.AddEventFragment;

/**
 * Created by sunkuan on 2015/4/8.
 */
public class AddEventActivity extends ActionBarActivity {
    private String startCCYY;
    private String startMM;
    private String startDD;
    private String startHH;
    private String startTT;
    private String endCCYY;
    private String endMM;
    private String endDD;
    private String endHH;
    private String endTT;
    private String title;
    private String detail;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager fm = this.getSupportFragmentManager();
        AddEventFragment addEventFragment = (AddEventFragment) fm.findFragmentById(R.id.id_add_event_fragment_container);
        if(addEventFragment == null){
            addEventFragment = new AddEventFragment();
            fm.beginTransaction()
                    .add(R.id.id_add_event_fragment_container,addEventFragment)
                    .commit();
            }
        getDefaultValue();
        }

    private void getDefaultValue(){
        startCCYY = mMonthData.getCurrentCCYY();
        startMM = mMonthData.getCurrentMM();
        startDD = mMonthData.getCurrentDD();
        startHH = mMonthData.getCurrentHH();
        startTT = mMonthData.getCurrentTT();
        endCCYY = startCCYY;
        startMM = mMonthData.getCurrentMM();
        startDD = mMonthData.getCurrentDD();
        startHH = mMonthData.getDefaultEndHH();
        startTT = mMonthData.getCurrentTT();
        title = "untitled event";
//        detail = "Untitled event occurs on" + startCCYY+" "+startMMDD+" "+startHHMM;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_event, menu);
        return true;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        /**
         * requestCode:
         *              0:  Start Date Picker
         *              1:  End Date Picker
         * resultCode:
         *              0:  success - two steps all passed
         *              1:  fail - only picked date
         *              2:  fail - only picked time?
         */
        switch (requestCode){
            case 0:
                    if(resultCode == 0){
                        if(data == null)
                            return;
//                        Toast.makeText(this,data.getStringExtra("date")+" --- "+data.getStringExtra("time"),Toast.LENGTH_LONG).show();
                        startCCYY = data.getStringExtra("CCYY");
                        startMM = data.getStringExtra("MM");
                        startDD = data.getStringExtra("DD");
                        startHH = data.getStringExtra("HH");
                        startTT = data.getStringExtra("TT");
                    }else{
                        return;
                    }
                break;
            case 1:
                if(resultCode == 0){
                    if(data == null)
                        return;
//                    Toast.makeText(this,data.getStringExtra("date")+" --- "+data.getStringExtra("time"),Toast.LENGTH_LONG).show();
                    endCCYY = data.getStringExtra("CCYY");
                    endMM = data.getStringExtra("MM");
                    endDD = data.getStringExtra("DD");
                    endHH = data.getStringExtra("HH");
                    endTT = data.getStringExtra("TT");
                }else{
                    return;
                }
                break;
            default:
                break;
        }
        return;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("SimCal - ",startCCYY+startMM+startDD+startHH+startTT);
        return true;
    }
}
