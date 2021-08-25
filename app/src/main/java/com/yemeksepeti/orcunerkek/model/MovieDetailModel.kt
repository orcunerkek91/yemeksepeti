package com.yemeksepeti.orcunerkek.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetailModel() : Parcelable {

    @SerializedName("backdrop_path")
    @Expose
    var backdrop_path: String? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("genres")
    @Expose
    var genres: List<GenresModel>? = null

    @SerializedName("vote_average")
    @Expose
    var vote_average: Double? = null

    @SerializedName("poster_path")
    @Expose
    var poster_path: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    constructor(parcel: Parcel) : this() {
        backdrop_path = parcel.readString()
        overview = parcel.readString()
        genres = parcel.createTypedArrayList(GenresModel)
        vote_average = parcel.readValue(Double::class.java.classLoader) as? Double
        poster_path = parcel.readString()
        title = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(backdrop_path)
        parcel.writeString(overview)
        parcel.writeTypedList(genres)
        parcel.writeValue(vote_average)
        parcel.writeString(poster_path)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetailModel> {
        override fun createFromParcel(parcel: Parcel): MovieDetailModel {
            return MovieDetailModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetailModel?> {
            return arrayOfNulls(size)
        }
    }


}