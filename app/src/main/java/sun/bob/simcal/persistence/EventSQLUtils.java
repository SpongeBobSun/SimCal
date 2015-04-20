package sun.bob.simcal.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import sun.bob.simcal.R;
import sun.bob.simcal.model.mEventBean;

/**
 * Created by sunkuan on 15/4/7.
 */
public class EventSQLUtils {
    private static EventSQLUtils staticInstance;
    private static Context appContext;
    private final String DB_PATH = "/data/data/sun.bob.simcal/databases/";
    private final String DB_FILENAME = "event.db";

    SQLiteDatabase database;

    private EventSQLUtils(Context context){
        appContext = context;
        initialCopy();
    }

    public static EventSQLUtils getStaticInstance(Context context){
        if(staticInstance == null){
            staticInstance = new EventSQLUtils(context);
            return staticInstance;
        }
        return staticInstance;
    }
    private void initialCopy(){
        if ((new File(DB_PATH + DB_FILENAME)).exists() == false) {
            File dbfile = new File(DB_PATH);
            if (!dbfile.exists()) {
                dbfile.mkdir();
            }

            try {

                InputStream is = appContext.getResources().openRawResource(R.raw.events);

                OutputStream os = new FileOutputStream(DB_PATH + DB_FILENAME);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }

                os.flush();
                os.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            return;
        }
    }
    private void openSQL(){
        database = SQLiteDatabase.openOrCreateDatabase(DB_PATH + DB_FILENAME, null);
    }
    private void closeSQL(){
        database.close();
    }

    public void addEvent(mEventBean eventBean){
        openSQL();
        ContentValues values = new ContentValues();
        values.put("ccyy",eventBean.getYear());
        values.put("mm",eventBean.getMonth());
        values.put("dd",eventBean.getDay());
        values.put("hh",eventBean.getHour());
        values.put("minute",eventBean.getMinute());
        values.put("title",eventBean.getTitle());
        values.put("detail",eventBean.getDetail());
        values.put("status",eventBean.getStatus());
        database.insert("events",null,values);
        closeSQL();
    }
    public void deleteEvent(mEventBean eventBean){
        openSQL();
        database.delete("events","id=?",new String[]{String.valueOf(eventBean.get_id())});
        closeSQL();
    }
    public void modityEvent(mEventBean eventBean){
        openSQL();
        ContentValues values = new ContentValues();
        values.put("ccyy",eventBean.getYear());
        values.put("mm",eventBean.getMonth());
        values.put("dd",eventBean.getDay());
        values.put("hh",eventBean.getHour());
        values.put("minute",eventBean.getMinute());
        values.put("title",eventBean.getTitle());
        values.put("detail",eventBean.getDetail());
        values.put("status",eventBean.getDetail());
        database.update("events",values,"_id=?",new String[]{String.valueOf(eventBean.get_id())});
        closeSQL();
    }
    public ArrayList<mEventBean> getDayEvent(int ccyy,int mm,int dd){
        openSQL();
        ArrayList<mEventBean> ret = new ArrayList<>();

        Cursor cursor = database.query("events",
                                        new String[]{"_id","title","detail","hh","minute","status"},
                                        "ccyy=? and mm=? and dd=?",
                                        new String[]{String.valueOf(ccyy),
                                                     String.valueOf(mm),
                                                     String.valueOf(dd)},
                                        null, null, null);
        mEventBean newBean = null;
        while (cursor.moveToNext()) {
            newBean = new mEventBean();
            newBean.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            newBean.setDay(dd);
            newBean.setMonth(mm);
            newBean.setYear(ccyy);
            newBean.setHour(cursor.getInt(cursor.getColumnIndex("hh")));
            newBean.setMinute(cursor.getInt(cursor.getColumnIndex("minute")));
            newBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            newBean.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            newBean.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            ret.add(newBean);
        }
        cursor.close();
        closeSQL();
        return ret;
    }
    public ArrayList<mEventBean> getMonthEvent(int ccyy, int mm){
        openSQL();
        ArrayList<mEventBean> ret = new ArrayList<>();

        Cursor cursor = database.query("events",
                new String[]{"_id","title","detail","dd","hh","minute","status"},
                "ccyy=? and mm=?",
                new String[]{String.valueOf(ccyy),
                        String.valueOf(mm)},
                null, null, null);
        mEventBean newBean = null;
        while (cursor.moveToNext()) {
            newBean = new mEventBean();
            newBean.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            newBean.setDay(cursor.getInt(cursor.getColumnIndex("dd")));
            newBean.setMonth(mm);
            newBean.setYear(ccyy);
            newBean.setHour(cursor.getInt(cursor.getColumnIndex("hh")));
            newBean.setMinute(cursor.getInt(cursor.getColumnIndex("minute")));
            newBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            newBean.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            newBean.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
            ret.add(newBean);
        }
        cursor.close();
        closeSQL();
        return ret;
    }
    public mEventBean getEventById(int id){
        mEventBean ret = new mEventBean();
        openSQL();
        Cursor cursor = database.query("events",
                                       new String[]{"_id","title","detail","hh","minute","ccyy","mm","dd","status"}
                                        ,null,null,null,null,null);
        if(cursor.getCount() == 0){
            return null;
        }
        while(cursor.moveToNext()){
            ret.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            ret.setDay(cursor.getInt(cursor.getColumnIndex("dd")));
            ret.setMonth(cursor.getInt(cursor.getColumnIndex("mm")));
            ret.setYear(cursor.getInt(cursor.getColumnIndex("ccyy")));
            ret.setHour(cursor.getInt(cursor.getColumnIndex("hh")));
            ret.setMinute(cursor.getInt(cursor.getColumnIndex("minute")));
            ret.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            ret.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            ret.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
        }
        cursor.close();
        closeSQL();
        return ret;
    }
}
