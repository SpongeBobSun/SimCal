package sun.bob.simcal.model;

import java.util.ArrayList;

/**
 * Created by sunkuan on 15/4/6.
 */
public class mTimeContainer {
    private ArrayList<mEventBean> content;
    private int hour;
    public mTimeContainer(int arg){
        this.hour = arg;
        content = new ArrayList();
    }
    public ArrayList getContent() {
        return content;
    }
    public void addEvent(mEventBean eventBean){
        content.add(eventBean);
    }
    public int getSize(){return content.size();}
    public int getHour() {
        return hour;
    }
}
