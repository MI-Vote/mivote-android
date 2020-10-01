package io.michiganelections.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoterRegistration(
  val registered: Boolean,
  val absentee: Boolean,
  val absenteeApplicationReceived: String?, //date
  val absenteeBallotSent: String?, //date
  val absenteeBallotRecieved: String?, //date
  val pollingLocation: List<String>?,
  val dropboxLocation: List<String>?,
  val recentlyMoved: Boolean,
  val precinct: Precinct?,
  val districts: List<District>?
)
