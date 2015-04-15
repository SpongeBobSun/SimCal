package sun.bob.simcal.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by sunkuan on 15/4/6.
 */
public class mTimeData {
    private ArrayList<mTimeContainer> content;
    public mTimeData(Context context){
        content = new ArrayList<>();
        populateTimeLine();
    }
    public void addEvent(mEventBean bean){

    }
    private void populateTimeLine(){
        for(int i = 0;i < 24;i++){
        content.add(new mTimeContainer(i));
        }
    }

    public ArrayList<mTimeContainer> getContent() {
        return content;
    }
}
