package sun.bob.simcal.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import sun.bob.simcal.R;

/**
 * Created by sunkuan on 15/4/8.
 */
public class DatePickerActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_date_picker);
        Button okButton = (Button)findViewById(R.id.id_date_picker_ok_button);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DatePickerActivity.this,TimePickerActivity.class);
                DatePicker datePicker = (DatePicker) findViewById(R.id.id_date_picker_widget);
                intent.putExtra("CCYY",String.valueOf(datePicker.getYear()));
                intent.putExtra("MM",String.valueOf(datePicker.getMonth()+1));
                intent.putExtra("DD",String.valueOf(datePicker.getDayOfMonth()));
                startActivityForResult(intent, 0);

            }
        });
        Button cancelButton = (Button) findViewById(R.id.id_date_picker_cancle_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(-1,null);
                finish();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int respondCode, Intent data){
        if (respondCode == 0){
            setResult(0,data);
        }else{
            setResult(-1,null);
        }
        finish();
    }
}
