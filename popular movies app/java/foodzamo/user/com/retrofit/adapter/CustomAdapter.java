package foodzamo.user.com.retrofit.adapter;

/**
 * Created by Satish on 3/12/2017.
 */


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import foodzamo.user.com.retrofit.Dummy;
import foodzamo.user.com.retrofit.MainActivity;
import foodzamo.user.com.retrofit.MovieDescription;
import foodzamo.user.com.retrofit.R;
import foodzamo.user.com.retrofit.SQLite.DatabaseOperations;
import foodzamo.user.com.retrofit.model.Movie;

public class CustomAdapter extends ArrayAdapter<String> {

    private final Activity context;
    TextView txtTitle;
    private String[] abcd;
    ProgressDialog progressDialog;
    private List<Movie> movies;
    ImageView imageview;
    TextView descr,rating,release;
    DatabaseOperations databaseOperations;
    ImageView image_favourites;
    final ImageView image=null;


    public CustomAdapter(Activity context, String[]abcd, ProgressDialog progressDialog, List<Movie> movies, DatabaseOperations databaseOperations) {
        super(context, R.layout.mylist,abcd);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.abcd=abcd;
        this.progressDialog=progressDialog;
        this.movies=movies;
        this.databaseOperations=databaseOperations;

    }


    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        txtTitle=(TextView)rowView.findViewById(R.id.title);
        imageview=(ImageView)rowView.findViewById(R.id.load_image);
        descr=(TextView)rowView.findViewById(R.id.description);
        rating=(TextView)rowView.findViewById(R.id.rating);
        image_favourites=(ImageView)rowView.findViewById(R.id.add_to_favourites);
        //release=(TextView)view.findViewById(R.id.release_da);

        txtTitle.setText(movies.get(position).getTitle());
        //descr.setText(movies.get(position).getOverview());
        rating.setText(movies.get(position).getVoteAverage().toString());

        String s_title,s_rating;

        final ImageView image=image_favourites;

        s_title=movies.get(position).getTitle();
        s_rating=movies.get(position).getVoteAverage().toString();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show();


                Resources resources = context.getResources();
                image.setImageDrawable(resources.getDrawable(R.drawable.favourite_full));
                //image_favourites.setImageResource(R.drawable.favourite_full);
                //return;
            }
        });

       // databaseOperations.putInformation(databaseOperations,s_title,s_rating);
        //release_date.setText(movies.get(position).getReleaseDate());




        String url="https://image.tmdb.org/t/p/w500"+movies.get(position).getPosterPath();

        Picasso.with(context).load(url).into(imageview);

        progressDialog.dismiss();

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"hello",Toast.LENGTH_SHORT).show();

                String s= String.valueOf(movies.get(position).getId());

                Intent i=new Intent(context, MovieDescription.class);
                i.putExtra("id_key",s);
                context.startActivity(i);
                //context.startActivity(new Intent(context, Dummy.class));
            }
        });





        return rowView;

    };

}
