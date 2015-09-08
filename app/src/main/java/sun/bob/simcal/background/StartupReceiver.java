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
        Intent eventIntent = new Intent(context,EventService.class);
        eventIntent.putExtra("CMD",EventService.CMD_PULL);
        context.startService(eventIntent);
    }
}
