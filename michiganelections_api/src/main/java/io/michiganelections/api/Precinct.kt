package io.michiganelections.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Precinct(
  val id: Long,
  val url: String,
  val county: String,
  val jurisdiction: String,
  val ward: String?,
  val number: String
)
