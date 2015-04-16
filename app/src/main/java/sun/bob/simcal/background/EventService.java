package sun.bob.simcal.background;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

import sun.bob.simcal.MainActivity;
import sun.bob.simcal.NotificationActivity;
import sun.bob.simcal.R;
import sun.bob.simcal.model.mEventBean;
import sun.bob.simcal.persistence.EventSQLUtils;

/**
 * Created by sunkuan on 2015/4/15.
 */
public class EventService extends IntentService {

    public EventService() {
        super("start_event_service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//        Log.i("SimCal","Back ground service checking...");
        Calendar calendar = Calendar.getInstance();
        int currentHour = calendar.get(Calendar.HOUR);
        int currentMin = calendar.get(Calendar.MINUTE);
        ArrayList<mEventBean> eventBeans = EventSQLUtils.getStaticInstance(getApplicationContext()).getDayEvent(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
        for(int i = 0;i<eventBeans.size();i++){
            if((currentHour == eventBeans.get(i).getHour())||(currentMin == eventBeans.get(i).getMinute())){
                sendNotification(eventBeans.get(i));
            }
        }
    }
    private void sendNotification(mEventBean bean){
        NotificationManager nm = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher,bean.getTitle(),System.currentTimeMillis());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        notification.setLatestEventInfo(getApplicationContext(),"SimCal",bean.getTitle(),pendingIntent);
        notification.defaults = Notification.DEFAULT_ALL;
        nm.notify(100,notification);
        Intent popupIntent = new Intent(getApplicationContext(), NotificationActivity.class);
        popupIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(popupIntent);
    }


}
