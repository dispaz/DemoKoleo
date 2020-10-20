package com.testkoleo.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Station(
    @PrimaryKey
    val id: Int,
    val name: String,
    @SerializedName("name_slug")
    val nameSlug: String,
    val latitude: Double,
    val longitude: Double,
    val hits: Int,
    val ibnr: Int,
    val city: String,
    val region: String,
    val country: String,
    @SerializedName("localised_name")
    val localisedName: String?
) : Parcelable