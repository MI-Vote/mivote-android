package io.michiganelections.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SampleBallot(
  @Json(name = "id")
  val id: Int,

  @Json(name = "precinct")
  val precinct: Precinct,

  @Json(name = "election")
  val election: Election,

  @Json(name = "mvic_url")
  val ballotUrl: String
)
