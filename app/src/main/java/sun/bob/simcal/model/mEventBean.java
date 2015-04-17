package sun.bob.simcal.model;

/**
 * Created by sunkuan on 15/4/6.
 */
public class mEventBean {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String title;
    private String detail;
    private int _id;
    private int status;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month < 13) {
            this.month = month;
        }else{
            this.month = (month - 12);
            this.setYear(this.year+1);
        }
    }

    public int getDay() {
        return day;
    }

    //Todo
    //This function cause a bug when setHour is called with an improper argument.
    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if(hour < 24){
            this.hour = hour;
        }else{
            this.hour = (hour - 24);
            this.setDay(this.day+1);
        }
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        if(minute < 60) {
            this.minute = minute;
        }else{
            this.minute = minute - 60;
            this.setHour(this.getHour()+1);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
