package sun.bob.simcal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import sun.bob.simcal.model.mEventBean;
import sun.bob.simcal.persistence.EventSQLUtils;

/**
 * Created by sunkuan on 15/4/15.
 */
public class NotificationActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_notification);
        final mEventBean bean = EventSQLUtils.getStaticInstance(this).getEventById(getIntent().getIntExtra("eventID",-1));
        TextView title = (TextView) findViewById(R.id.id_text_view_popup_event_title);
        title.setText(bean.getTitle());
        Button viewButton = (Button) findViewById(R.id.id_button_popup_view);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this,EditEventActivity.class);
                intent.putExtra("eventID",bean.get_id());
                intent.putExtra("startCCYY",bean.getYear());
                intent.putExtra("startMM",bean.getMonth());
                intent.putExtra("startDD",bean.getDay());
                intent.putExtra("startHH",bean.getHour());
                intent.putExtra("startTT",bean.getMinute());
                intent.putExtra("title",bean.getTitle());
                intent.putExtra("detail",bean.getDetail());
                startActivity(intent);
                finish();
            }
        });
        Button laterButton = (Button) findViewById(R.id.id_button_popup_later);
        laterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setMinute(bean.getMinute() + 5);
                EventSQLUtils.getStaticInstance(NotificationActivity.this).modityEvent(bean);
                Intent intent = new Intent("intent_date_cell_click");
                intent.putExtra("CCYY",bean.getYear());
                intent.putExtra("MM",bean.getMonth());
                intent.putExtra("DD",bean.getDay());
                sendBroadcast(intent);
                finish();
            }
        });
        Button dismissButton = (Button) findViewById(R.id.id_button_popup_dismiss);
        dismissButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
