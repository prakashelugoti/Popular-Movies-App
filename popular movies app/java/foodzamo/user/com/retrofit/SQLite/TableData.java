package foodzamo.user.com.retrofit.SQLite;

/**
 * Created by Satish on 3/15/2017.
 */

import android.provider.BaseColumns;

public class TableData {

    public TableData()
    {

    }

    public static abstract class TableInfo implements BaseColumns
    {
        public static final String USER_NAME="user_name";
        public static final String USER_PASS="user_pass";
        public static final String DATABASE_NAME="movies_info";
        public static final String TABLE_NAME="reg_info";

        public static final String MOV_NAME="user_name";
        public static final String MOV_PASS="user_pass";
        public static final String MOV_TABLE_NAME="reg_info";


    }
}
