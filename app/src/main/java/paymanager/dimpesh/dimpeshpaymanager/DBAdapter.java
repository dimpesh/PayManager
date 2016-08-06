package paymanager.dimpesh.dimpeshpaymanager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {
    static String name = "MoneyManager.sqlite";
    static String path = "";
    static ArrayList<Users> a;
    static SQLiteDatabase sdb;

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

    private DBAdapter(Context v) {
        super(v, name, null, 1);
        path = "/data/data/" + v.getApplicationContext().getPackageName()
                + "/databases";
    }

    public boolean checkDatabase() {
        SQLiteDatabase db = null;
        try {
            db = SQLiteDatabase.openDatabase(path + "/" + name, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {

        }

        if (db == null) {
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public static synchronized DBAdapter getDBAdapter(Context v) {
        return (new DBAdapter(v));
    }

    public void createDatabase(Context v) {
        this.getReadableDatabase();
        try {
            InputStream myInput = v.getAssets().open(name);
            // Path to the just created empty db
            String outFileName = path + "/" + name;
            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);
            // transfer bytes from the inputfile to the outputfile
            byte[] bytes = new byte[1024];
            int length;
            while ((length = myInput.read(bytes)) > 0) {
                myOutput.write(bytes, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();

		/*	

			
			
			InputStream is = v.getAssets().open("quiz.sqlite");
			// System.out.println(is.available());
			System.out.println(new File(path + "/" + name).getAbsolutePath());
			FileOutputStream fos = new FileOutputStream(path + "/" + name);
			int num = 0;
			while ((num = is.read()) > 0) {
				fos.write((byte) num);
			}
			fos.close();
			is.close();*/
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void openDatabase() {
        try {
            sdb = SQLiteDatabase.openDatabase(path + "/" + name, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int insertDB(String name, String email, float amt, String phone, String password) {

        Cursor c = sdb.rawQuery("SELECT * from users where username='" + name + "' and phno='" + phone + "'", null);
        if (c.getCount() > 0)
            return -1;

        ContentValues cv = new ContentValues();
        cv.put("username", name);
        cv.put("email", email);
        cv.put("amt", amt);
        cv.put("phno", phone);
        cv.put("password", password);

        sdb.insert("users", null, cv);

        Cursor c1 = sdb.rawQuery("SELECT * from users where username='" + name + "' and password='" + password + "' and phno='" + phone + "'", null);
        if (c1.moveToNext()) {
            c1.moveToFirst();
            return c1.getInt(0);
        } else
            return 0;
    }

    public ArrayList<Users> getData(String name, String password) {

//		Cursor c1 = sdb.rawQuery("SELECT * from users where username='dimpesh' and password='dimpesh123'", null);
        Cursor c1 = sdb.rawQuery("SELECT * from users where username='" + name + "' and password='" + password + "'", null);
        a = new ArrayList<Users>();
        while (c1.moveToNext()) {
            Users q1 = new Users();
            q1.setId(c1.getInt(0));
            q1.setUsername(c1.getString(1));
            q1.setEmail(c1.getString(2));
            q1.setAmt(c1.getFloat(3));
            q1.setPhno(c1.getString(4));
            q1.setPassword(c1.getString(5));
            a.add(q1);
        }
        return a;
    }

    public Users getRegisterData(String name, String password) {

        Cursor c1 = sdb.rawQuery("SELECT * from users where username='" + name + "' and password='" + password + "'", null);
        if (c1.moveToNext()) {
            c1.moveToFirst();
            Users q1 = new Users();
            q1.setId(c1.getInt(0));
            q1.setUsername(c1.getString(1));
            q1.setEmail(c1.getString(2));
            q1.setAmt(c1.getFloat(3));
            q1.setPhno(c1.getString(4));
            q1.setPassword(c1.getString(5));
            return q1;
        }
        return null;
    }

    public void delete(String name) {

        sdb.execSQL("DELETE FROM BasicInfo WHERE Name='" + name + "'");
    }
}
