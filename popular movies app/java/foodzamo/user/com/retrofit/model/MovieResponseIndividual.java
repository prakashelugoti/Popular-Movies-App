package foodzamo.user.com.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Satish on 3/12/2017.
 */

public class MovieResponseIndividual {

    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("original_language")
    private String original_language;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("tagline")
    private String tagline;






    public String getPoster_path()
    {
        return poster_path;
    }

   public String getTitle()
   {
       return title;
   }

    public String getOverview()
    {
        return overview;
    }

    public String getOriginal_language()
    {
        return original_language;
    }

    public int getRuntime()
    {
        return runtime;
    }

    public String getTagline()
    {
        return tagline;
    }

    //assigning data
    public void setPoster(String poster_path)
    {
        this.poster_path=poster_path;
    }

    public void setTitle(String title)
    {
        this.title=title;
    }

    public void setOverview(String overview)
    {
        this.overview=overview;
    }

    public void setOriginal_language(String original_language)
    {
        this.original_language=original_language;
    }

    public void setRuntime(Integer runtime)
    {
        this.runtime=runtime;
    }

    public void setTagline(String tagline)
    {
        this.tagline=tagline;
    }

}
