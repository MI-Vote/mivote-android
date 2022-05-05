package io.michiganelections.api.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Precinct(
  val id: Int,
  val county: String,
  val jurisdiction: String,
  val ward: String?,
  val number: String
): Parcelable
