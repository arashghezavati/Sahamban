package com.example.android.sahamban.Network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SahambanProperty(

    val id:String,
    @Json(name ="img_src")val imgSrcUrl:String,
    val type: String,
    val price:Double
):Parcelable