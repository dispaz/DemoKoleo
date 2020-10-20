package com.testkoleo.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class StationKeyword(
    @PrimaryKey
    val id: Int,
    val keyword: String,
    @SerializedName("station_id")
    val stationId: String
) : Parcelable