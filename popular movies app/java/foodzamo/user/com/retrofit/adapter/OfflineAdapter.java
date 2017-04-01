package foodzamo.user.com.retrofit.adapter;

/**
 * Created by Satish on 3/15/2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
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
import foodzamo.user.com.retrofit.MovieDescription;
import foodzamo.user.com.retrofit.R;
import foodzamo.user.com.retrofit.SQLite.DatabaseOperations;
import foodzamo.user.com.retrofit.model.Movie;

public class OfflineAdapter extends ArrayAdapter<String> {

    private final Activity context;
    TextView txtTitle;
    ImageView imageview;
    TextView descr,rating,release;
    DatabaseOperations databaseOperations;
    Cursor CR;
    String[] abcd;


    public OfflineAdapter(Activity context, DatabaseOperations databaseOperations, Cursor CR, String[] abcd) {
        super(context, R.layout.mylist,abcd);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.databaseOperations=databaseOperations;
        this.CR=CR;
        this.abcd=abcd;

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        txtTitle=(TextView)rowView.findViewById(R.id.title);
        imageview=(ImageView)rowView.findViewById(R.id.load_image);
        descr=(TextView)rowView.findViewById(R.id.description);
        rating=(TextView)rowView.findViewById(R.id.rating);
        //release=(TextView)view.findViewById(R.id.release_da);



        CR.moveToPosition(position);
        txtTitle.setText(CR.getString(0));
        rating.setText(CR.getString(1));

        //rating.setText("2.3");
        imageview.setImageResource(R.drawable.no_internet);
        //Toast.makeText(context,"No Internet!!",Toast.LENGTH_SHORT).show();


        //databaseOperations.putInformation(databaseOperations,s_title,s_rating);
        //release_date.setText(movies.get(position).getReleaseDate());




        //String url="https://image.tmdb.org/t/p/w500"+movies.get(position).getPosterPath();

        //Picasso.with(context).load(url).into(imageview);

        //progressDialog.dismiss();

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"No Internet!!",Toast.LENGTH_SHORT).show();

                //context.startActivity(new Intent(context, Dummy.class));
            }
        });





        return rowView;

    };

}

