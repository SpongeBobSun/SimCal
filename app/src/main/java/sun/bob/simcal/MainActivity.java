package sun.bob.simcal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import sun.bob.simcal.model.mDateData;
import sun.bob.simcal.view.MainFragment;
import sun.bob.simcal.view.TodayFragment;
import sun.bob.simcal.view.TodoFragment;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {
    private MainFragment mainFragment;
    private TodayFragment todayFragment;
    private TodoFragment todoFragment;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("sun.bob.simcal.pull_service");
        sendBroadcast(serviceIntent);
    }

    private void initFragments(){
        FragmentManager fm = getSupportFragmentManager();
        mainFragment = new MainFragment();
        todayFragment = new TodayFragment();
        todoFragment = new TodoFragment();
        fm.beginTransaction().add(R.id.container,mainFragment,"mainFragment").commit();
        fm.beginTransaction().add(R.id.container,todayFragment,"todayFragment").commit();
        fm.beginTransaction().add(R.id.container,todoFragment,"todoFragment").commit();
        fm.beginTransaction().hide(todayFragment).commit();
        fm.beginTransaction().hide(todoFragment).commit();
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(mainFragment == null | todayFragment == null | todoFragment == null){
            initFragments();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (position){
            case 0:
                fragmentManager.beginTransaction().show(mainFragment).hide(todayFragment).hide(todoFragment).commit();

                break;
            case 1:
                fragmentManager.beginTransaction().show(todayFragment).hide(mainFragment).hide(todoFragment).commit();
                todayFragment.notifyDataSetChanged();
                break;
            case 2:
                fragmentManager.beginTransaction().show(todoFragment).hide(mainFragment).hide(todayFragment).commit();
                break;
            default:
                break;
        }
    }

//    public void onSectionAttached(int number) {
//        switch (number) {
//            case 1:
//                mTitle = getString(R.string.title_section1);
//                break;
//            case 2:
//                mTitle = getString(R.string.title_section2);
//                break;
//            case 3:
//                mTitle = getString(R.string.title_section3);
//                break;
//        }
//    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public mDateData getCurrentSelectDate(){
        return mainFragment.getCurrentSelectDate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

}
