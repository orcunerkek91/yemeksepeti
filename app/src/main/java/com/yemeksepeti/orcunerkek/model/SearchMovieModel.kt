package com.yemeksepeti.orcunerkek.model

import android.graphics.Movie
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchMovieModel() : Parcelable {

    @SerializedName("page")
    @Expose
    var page: Int? = null

    @SerializedName("results")
    @Expose
    var movie_result_model: ArrayList<MovieResultModel>? = null

    @SerializedName("total_results")
    @Expose
    var total_result: Int? = null

    @SerializedName("total_pages")
    @Expose
    var total_pages: Int? = null

    constructor(parcel: Parcel) : this() {
        page = parcel.readValue(Int::class.java.classLoader) as? Int
        total_result = parcel.readValue(Int::class.java.classLoader) as? Int
        total_pages = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<SearchMovieModel> {
        override fun createFromParcel(parcel: Parcel): SearchMovieModel {
            return SearchMovieModel(parcel)
        }

        override fun newArray(size: Int): Array<SearchMovieModel?> {
            return arrayOfNulls(size)
        }
    }




}