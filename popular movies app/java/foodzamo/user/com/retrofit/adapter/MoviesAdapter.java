package foodzamo.user.com.retrofit.adapter;

/**
 * Created by Satish on 3/11/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Cache;
import com.squareup.picasso.Picasso;

import java.util.List;

import foodzamo.user.com.retrofit.Dummy;
import foodzamo.user.com.retrofit.MainActivity;
import foodzamo.user.com.retrofit.MovieDescription;
import foodzamo.user.com.retrofit.R;
import foodzamo.user.com.retrofit.model.Movie;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    ProgressDialog progressDialog;

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context, ProgressDialog progressDialog) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
        this.progressDialog=progressDialog;
    }
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView loadImage;


        public MovieViewHolder(View v) {
            super(v);


            loadImage=(ImageView)v.findViewById(R.id.load_image);
            cardView=(CardView)v.findViewById(R.id.card_view);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            movieTitle = (TextView) v.findViewById(R.id.title);
            data = (TextView) v.findViewById(R.id.subtitle);
            movieDescription = (TextView) v.findViewById(R.id.description);
            rating = (TextView) v.findViewById(R.id.rating);


        }
    }



    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);


    }


    @Override
    public void onBindViewHolder(final MovieViewHolder holder, final int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        //holder.movieDescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());


        progressDialog.dismiss();

        String url="https://image.tmdb.org/t/p/w500"+movies.get(position).getPosterPath();
        Picasso.with(context).load(url).resize(80,80).into(holder.loadImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context.startActivity(new Intent(context,Dummy.class));

            }
        });

    }



    @Override
    public int getItemCount() {
        return movies.size();
    }
}
