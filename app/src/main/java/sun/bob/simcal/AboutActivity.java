package sun.bob.simcal;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by sunkuan on 15/4/18.
 */
public class AboutActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.about_activity);
    }
}
