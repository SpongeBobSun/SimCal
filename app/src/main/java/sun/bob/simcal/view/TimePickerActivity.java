package sun.bob.simcal.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import sun.bob.simcal.R;

/**
 * Created by sunkuan on 15/4/8.
 */
public class TimePickerActivity extends Activity {
    private String dateValue;
    private String CCYY;
    private String MM;
    private String DD;
    private String HH;
    private String TT;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_time_picker);

        dateValue = getIntent().getStringExtra("date");
        CCYY = getIntent().getStringExtra("CCYY");
        MM = getIntent().getStringExtra("MM");
        DD = getIntent().getStringExtra("DD");
        Button okButton = (Button)findViewById(R.id.id_date_picker_ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultCode = 0;
                Intent result = new Intent();
                TimePicker timePicker = (TimePicker) findViewById(R.id.id_time_picker_widget);
                result.putExtra("CCYY",CCYY);
                result.putExtra("MM",MM);
                result.putExtra("DD",DD);
                result.putExtra("HH",timePicker.getCurrentHour().toString());
                result.putExtra("TT",timePicker.getCurrentMinute().toString());
                setResult(resultCode, result);
                finish();
            }
        });
        Button cancleButton = (Button) findViewById(R.id.id_date_picker_cancle_button);
        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(-1,null);
                finish();
            }
        });
    }
}
