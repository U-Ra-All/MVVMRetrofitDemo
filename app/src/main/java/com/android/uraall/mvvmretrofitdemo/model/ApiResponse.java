package com.android.uraall.mvvmretrofitdemo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_Movies")
    @Expose
    private Integer totalMovies;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("Movies")
    @Expose
    private List<Movie> Movies = null;
    public final static Parcelable.Creator<ApiResponse> CREATOR = new Creator<ApiResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ApiResponse createFromParcel(Parcel in) {
            return new ApiResponse(in);
        }

        public ApiResponse[] newArray(int size) {
            return (new ApiResponse[size]);
        }

    }
            ;

    protected ApiResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.Movies, (com.android.uraall.mvvmretrofitdemo.model.Movie.class.getClassLoader()));
    }

    public ApiResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(Integer totalMovies) {
        this.totalMovies = totalMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Movie> getMovies() {
        return Movies;
    }

    public void setMovies(List<Movie> Movies) {
        this.Movies = Movies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalMovies);
        dest.writeValue(totalPages);
        dest.writeList(Movies);
    }

    public int describeContents() {
        return 0;
    }

}
