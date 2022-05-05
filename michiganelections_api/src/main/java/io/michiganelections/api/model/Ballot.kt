package io.michiganelections.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Ballot(
  val id: Int,
  @Json(name = "mvic_url")
  val mvicUrl: String,
  val election: Election,
  val precinct: Precinct
): Parcelable
