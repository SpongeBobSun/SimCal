package sun.bob.simcal.persistence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sunkuan on 15/4/18.
 */
public class PreferenceUtils {
    private static PreferenceUtils staticInstance;
    private boolean sound;
    private boolean vibrate;
    private int delayMin;
    private SharedPreferences sharedPreferences;

    private PreferenceUtils(Context context){
        sharedPreferences = context.getSharedPreferences("app_settings",0);
    }

    public static PreferenceUtils getInstance(Context context){
        if(staticInstance == null){
            staticInstance = new PreferenceUtils(context);
        }
        return staticInstance;
    }

    public boolean isSound() {
        sound = sharedPreferences.getBoolean("sound",true);
        return sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
        sharedPreferences.edit().putBoolean("sound",sound).commit();
    }

    public boolean isVibrate() {
        vibrate = sharedPreferences.getBoolean("vibrate",true);
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
        sharedPreferences.edit().putBoolean("vibrate",sound).commit();
    }

    public void setDelayMin(int delayMin) {
        this.delayMin = delayMin;
        sharedPreferences.edit().putInt("delayMin",delayMin);
    }

    public int getDelayMin() {
        delayMin = sharedPreferences.getInt("delayMin",delayMin);
        return delayMin;
    }
}
