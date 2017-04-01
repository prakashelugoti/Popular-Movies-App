package foodzamo.user.com.retrofit;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import foodzamo.user.com.retrofit.SQLite.DatabaseOperations;

public class NoInternet extends AppCompatActivity {
    DatabaseOperations dob;
    Cursor CR;

    TextView local_data;

    String data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
        dob=new DatabaseOperations(this);
        CR=dob.getInformation(dob);

        local_data=(TextView)findViewById(R.id.local_data);

       if(dob.isCursorEmpty(CR))
       {
           local_data.setText("No Data!");
       }
        else
       {
           CR.moveToFirst();
           do {
               data=data+CR.getString(0)+"\n";
           }while (CR.moveToNext());

           local_data.setText(data);
       }



    }

    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
