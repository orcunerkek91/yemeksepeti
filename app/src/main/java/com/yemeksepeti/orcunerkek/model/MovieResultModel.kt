package com.yemeksepeti.orcunerkek.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResultModel() : Parcelable {

    @SerializedName("poster_path")
    @Expose
    var poster_path: String? = null

    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("release_date")
    @Expose
    var release_date: String? = null

    @SerializedName("genre_ids")
    @Expose
    var genre_ids: IntArray? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("original_title")
    @Expose
    var original_title: String? = null

    @SerializedName("original_language")
    @Expose
    var original_language: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("backdrop_path")
    @Expose
    var backdrop_path: String? = null

    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null

    @SerializedName("vote_count")
    @Expose
    var vote_count: Int? = null

    @SerializedName("video")
    @Expose
    var video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
    var vote_average: Double? = null

    constructor(parcel: Parcel) : this() {
        poster_path = parcel.readString()
        adult = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        overview = parcel.readString()
        release_date = parcel.readString()
        genre_ids = parcel.createIntArray()
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        original_title = parcel.readString()
        original_language = parcel.readString()
        title = parcel.readString()
        backdrop_path = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        vote_count = parcel.readValue(Int::class.java.classLoader) as? Int
        video = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        vote_average = parcel.readValue(Double::class.java.classLoader) as? Double
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<MovieResultModel> {
        override fun createFromParcel(parcel: Parcel): MovieResultModel {
            return MovieResultModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieResultModel?> {
            return arrayOfNulls(size)
        }
    }

}