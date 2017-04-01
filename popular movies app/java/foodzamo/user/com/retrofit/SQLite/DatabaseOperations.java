package foodzamo.user.com.retrofit.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static foodzamo.user.com.retrofit.SQLite.TableData.TableInfo.MOV_TABLE_NAME;
import static foodzamo.user.com.retrofit.SQLite.TableData.TableInfo.TABLE_NAME;

/**
 * Created by Satish on 3/14/2017.
 */

public class DatabaseOperations extends SQLiteOpenHelper {

    public static final int database_version=1;
    public String CREATE_QUERY="CREATE TABLE "+ TABLE_NAME+"("+ TableData.TableInfo.USER_NAME+" TEXT,"+ TableData.TableInfo.USER_PASS+" TEXT );";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Databse operation:","Databased created");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
             db.execSQL(CREATE_QUERY);
        Log.d("Databse operation:","Table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(DatabaseOperations dob,String name, String pass)
    {
        SQLiteDatabase SQ=dob.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,name);
        cv.put(TableData.TableInfo.USER_PASS,pass);
        long k=SQ.insert(TABLE_NAME,null,cv);
        Log.d("Databse operation:","One row inserted!");
    }

    public Cursor getInformation(DatabaseOperations dob)
    {
        SQLiteDatabase SQ=dob.getReadableDatabase();
        String[] columns={TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS};
        Cursor CR=SQ.query(TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }

    public void putInformationMovie(DatabaseOperations dob,String name, String pass)
    {
        SQLiteDatabase SQ=dob.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(TableData.TableInfo.MOV_NAME,name);
        cv.put(TableData.TableInfo.MOV_PASS,pass);
        long k=SQ.insert(MOV_TABLE_NAME,null,cv);
        Log.d("Databse operation:","One row inserted!");
    }

    public Cursor getInformationMovie(DatabaseOperations dob)
    {
        SQLiteDatabase SQ=dob.getReadableDatabase();
        String[] columns={TableData.TableInfo.MOV_NAME, TableData.TableInfo.MOV_PASS};
        Cursor CR=SQ.query(MOV_TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }

    public Cursor getUserPass(DatabaseOperations dob, String user)
    {
   SQLiteDatabase SQ=dob.getReadableDatabase();
        String selection= TableData.TableInfo.USER_NAME+" LIKE ?";
        String[] columns= {TableData.TableInfo.USER_PASS};
        String[] args={user};

        Cursor CR=SQ.query(TABLE_NAME,columns,selection,args,null,null,null);
        return CR;

    }

    public void delete_user(DatabaseOperations dob, String user_name, String user_pass)
    {
        String selection= TableData.TableInfo.USER_NAME+" LIKE ? AND "+ TableData.TableInfo.USER_PASS+" LIKE ?";
        //String[] columns={TableData.TableInfo.USER_PASS};

        String[] args={user_name,user_pass};

        SQLiteDatabase SQ=dob.getWritableDatabase();
        SQ.delete(TABLE_NAME,selection,args);


    }

    public void update_user(DatabaseOperations dob,String user_name, String user_pass, String new_name)
    {
          SQLiteDatabase SQ=dob.getWritableDatabase();
        String selection= TableData.TableInfo.USER_NAME+" LIKE ? AND "+ TableData.TableInfo.USER_PASS+" LIKE ? ";

        String[] args={user_name,user_pass};
        ContentValues contentValues=new ContentValues();
        contentValues.put(TableData.TableInfo.USER_NAME,new_name);
        SQ.update(TABLE_NAME,contentValues,selection,args);


    }

    public boolean isCursorEmpty(Cursor cursor){
        if(!cursor.moveToFirst() || cursor.getCount() == 0) return true;
        return false;
    }



}
