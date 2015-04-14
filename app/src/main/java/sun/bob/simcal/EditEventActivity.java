package sun.bob.simcal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import sun.bob.simcal.model.mEventBean;
import sun.bob.simcal.persistence.EventSQLUtils;


/**
 * Created by sunkuan on 15/4/11.
 */
public class EditEventActivity extends AddEventActivity {
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
    private int eventID;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        eventID = intent.getIntExtra("eventID",-1);
        startCCYY = String.valueOf(intent.getIntExtra("startCCYY",-1));
        startMM = String.valueOf(intent.getIntExtra("startMM",-1));
        startDD = String.valueOf(intent.getIntExtra("startDD",-1));
        startHH = String.valueOf(intent.getIntExtra("startHH",-1));
        startTT = String.valueOf(intent.getIntExtra("startTT",-1));
        title = intent.getStringExtra("title");
        detail = intent.getStringExtra("detail");
        if(detail == null){
            detail = " ";
        }
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
        if (item.getItemId() == R.id.action_add_event) {
            title = ((EditText) findViewById(R.id.id_edit_text_event_title)).getText().toString();
            EventSQLUtils sqlUtils = EventSQLUtils.getStaticInstance(this);
            mEventBean eventBean = new mEventBean();
            eventBean.set_id(eventID);
            eventBean.setYear(Integer.valueOf(startCCYY));
            eventBean.setMonth(Integer.valueOf(startMM));
            eventBean.setDay(Integer.valueOf(startDD));
            eventBean.setHour(Integer.valueOf(startHH));
            eventBean.setMinute(Integer.valueOf(startTT));
            eventBean.setTitle(title);
            sqlUtils.modityEvent(eventBean);
            finish();
            return true;
        }
        return false;
    }
}
