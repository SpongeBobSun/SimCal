package sun.bob.simcal.background;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by sunkuan on 2015/4/15.
 */
public class EventService extends IntentService {
    public EventService() {
        super("start_event_service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("SimCal","Back ground service checking...");
    }
}
