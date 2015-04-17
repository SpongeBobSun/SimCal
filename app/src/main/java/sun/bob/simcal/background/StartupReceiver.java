package sun.bob.simcal.background;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sunkuan on 2015/4/15.
 */
public class StartupReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent eventIntent = new Intent(context,EventService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context,0,eventIntent,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1000*50,pendingIntent);
    }
}
