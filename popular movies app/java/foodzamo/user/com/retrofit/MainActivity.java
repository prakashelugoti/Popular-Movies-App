package foodzamo.user.com.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


import foodzamo.user.com.retrofit.SQLite.DatabaseOperations;
import foodzamo.user.com.retrofit.SQLite.Favourites;
import foodzamo.user.com.retrofit.adapter.CustomAdapter;
import foodzamo.user.com.retrofit.adapter.MoviesAdapter;
import foodzamo.user.com.retrofit.adapter.OfflineAdapter;
import foodzamo.user.com.retrofit.model.Movie;
import foodzamo.user.com.retrofit.model.MoviesResponse;
import foodzamo.user.com.retrofit.rest.ApiClient;
import foodzamo.user.com.retrofit.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
ListView list;

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "44a4cf4935167678639c9d8d1598b0bc";
    ProgressDialog progressDialog;
    List<Movie> data;
DatabaseOperations dob;
    Cursor CR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        dob=new DatabaseOperations(this);
        CR=dob.getInformation(dob);
        list=(ListView)findViewById(R.id.list);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY from themoviedb.org first!", Toast.LENGTH_LONG).show();
            return;
        }

        boolean flag=dob.isCursorEmpty(CR);

        if(isInternetOn()==0&&flag==true)
        {
            //finish();
            startActivity(new Intent(getApplicationContext(),NoInternet.class));
        }
        else if(isInternetOn()==0&&flag==false)
        {


            String[] abcd=new String[CR.getCount()];
            Toast.makeText(getApplicationContext(),String.valueOf(CR.getCount()),Toast.LENGTH_SHORT).show();
            OfflineAdapter adapter=new OfflineAdapter(MainActivity.this, dob,CR,abcd);
            list.setAdapter(adapter);
            //Toast.makeText(getApplicationContext(),"No Internet!!",Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(getApplicationContext(),NoInternet.class));
        }
        else
        {
            progressDialog.setMessage("Loading data...");
            progressDialog.setCancelable(false);
            progressDialog.show();


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                }
            });

            ApiInterface apiService =
                    ApiClient.getClient().create(ApiInterface.class);

            Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<MoviesResponse>() {
                @Override
                public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                    int statusCode = response.code();
                    List<Movie> movies = response.body().getResults();


                    if(!dob.isCursorEmpty(CR))
                    {
                        CR.moveToFirst();

                        do {
                            dob.delete_user(dob,CR.getString(0),CR.getString(1));
                        }while (CR.moveToNext());
                        data=movies;

                        Toast.makeText(getApplicationContext(),"Data deleted!!!",Toast.LENGTH_SHORT).show();
                    }

                    String rating="";

                    for(int i=0;i<movies.size();i++)
                    {
                        rating=movies.get(i).getVoteAverage().toString();
                        dob.putInformation(dob,movies.get(i).getTitle(),rating);
                    }

                    String[] abcd=new String[movies.size()];

                    //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext(),progressDialog));
                    CustomAdapter adapter=new CustomAdapter(MainActivity.this, abcd,progressDialog,movies,dob);
                    list.setAdapter(adapter);


                }

                @Override
                public void onFailure(Call<MoviesResponse> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, t.toString());
                }
            });
        }

    }

    public List<Movie> abcd()
    {
        return data;
    }

    public final int isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return 1;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            //Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return 0;
        }
        return 0;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            return true;
        }
        if (id == R.id.action_favourites) {
            finish();
            startActivity(new Intent(getApplicationContext(),Favourites.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
