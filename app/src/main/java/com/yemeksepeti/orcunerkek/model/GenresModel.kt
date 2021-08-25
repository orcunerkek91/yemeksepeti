package com.yemeksepeti.orcunerkek.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenresModel() : Parcelable{
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GenresModel> {
        override fun createFromParcel(parcel: Parcel): GenresModel {
            return GenresModel(parcel)
        }

        override fun newArray(size: Int): Array<GenresModel?> {
            return arrayOfNulls(size)
        }
    }
}