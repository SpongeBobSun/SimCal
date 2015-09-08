package sun.bob.simcal.background;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
public class EventService extends Service{

    private ServiceBinder binder = new ServiceBinder();

    public static final int CMD_PULL = 5020;
    public static final int CMD_TICK = 5021;

    @Override
    public void onCreate (){

    }

    @Override
    public int onStartCommand(Intent intent, int flags,int startId){
        switch (intent.getIntExtra("CMD", -1)){
            case CMD_PULL:
                Intent thisIntent = new Intent(this.getApplicationContext(),this.getClass());
                thisIntent.putExtra("CMD", CMD_TICK);
                PendingIntent pendingIntent = PendingIntent.getService(this.getApplicationContext(),0,thisIntent,0);
                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1000*50,pendingIntent);
                break;
            case CMD_TICK:
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMin = calendar.get(Calendar.MINUTE);
                ArrayList<mEventBean> eventBeans = EventSQLUtils.getStaticInstance(getApplicationContext()).getDayEvent(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH));
                for(int i = 0;i<eventBeans.size();i++){
                    if((currentHour == eventBeans.get(i).getHour())&&(currentMin == eventBeans.get(i).getMinute())){
                        sendNotification(eventBeans.get(i));
                    }
                }
                eventBeans.clear();
                break;
            default:
                break;
        }
        return START_REDELIVER_INTENT;
    }

    private void sendNotification(mEventBean bean){
        // TODO: 15/9/8 Use new notifications.
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
        popupIntent.putExtra("eventID",bean.get_id());
        this.startActivity(popupIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class ServiceBinder extends Binder{
        public Service getService(){
            return EventService.this;
        }
    }
}