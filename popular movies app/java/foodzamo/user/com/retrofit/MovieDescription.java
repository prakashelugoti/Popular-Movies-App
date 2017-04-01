package foodzamo.user.com.retrofit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import foodzamo.user.com.retrofit.adapter.MoviesAdapter;
import foodzamo.user.com.retrofit.model.Movie;
import foodzamo.user.com.retrofit.model.MovieResponseIndividual;
import foodzamo.user.com.retrofit.model.MoviesResponse;
import foodzamo.user.com.retrofit.rest.ApiClient;
import foodzamo.user.com.retrofit.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDescription extends AppCompatActivity {
String message;
    private final static String API_KEY = "44a4cf4935167678639c9d8d1598b0bc";
    ImageView loadimage;
    TextView title,description,duration,language,tagline;

    String s1,s2,s3,s4,s5,image_url;
    int runtime;

    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        progressDialog=new ProgressDialog(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            message = extras.getString("id_key");
            //The key argument here must match that used in the other activity
        }

        //Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        loadimage=(ImageView)findViewById(R.id.load_image);
        title=(TextView)findViewById(R.id.text_title);
        description=(TextView)findViewById(R.id.text_description);
        duration=(TextView)findViewById(R.id.text_duration);
        language=(TextView)findViewById(R.id.text_language);
        tagline=(TextView)findViewById(R.id.text_tagline);

        progressDialog.setMessage("Loading...");
        progressDialog.show();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        int id= Integer.parseInt(message);

        Call<MovieResponseIndividual> call = apiService.getMovieDetails(id,API_KEY);

        call.enqueue(new Callback<MovieResponseIndividual>() {
            @Override
            public void onResponse(Call<MovieResponseIndividual> call, Response<MovieResponseIndividual> response) {
                int statusCode = response.code();
                //List<Movie> movies = response.body().getResults();
                //recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext(),progressDialog));
                 image_url=response.body().getPoster_path();
                image_url="https://image.tmdb.org/t/p/w500"+image_url;

                title.setText(response.body().getTitle());
                description.setText(response.body().getOverview());

                int x=response.body().getRuntime();
                String t= String.valueOf(x)+" minutes";

                duration.setText(t);

                String lan=response.body().getOriginal_language();

                language.setText(set_lang(lan));

                tagline.setText(response.body().getTagline());

                 //Toast.makeText(getApplicationContext(),image_url,Toast.LENGTH_SHORT).show();


                Picasso.with(getApplicationContext()).load(image_url).into(loadimage);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<MovieResponseIndividual> call, Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());

                Toast.makeText(getApplicationContext(),"Error Occured!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        }

    public String set_lang(String text)
    {
        String lang=null;

        if(text.equals("ja"))
            lang="Japanese";
        else
           lang="English";

        return lang;
    }
    @Override
    public void onBackPressed()
    {
        //finish();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

}
