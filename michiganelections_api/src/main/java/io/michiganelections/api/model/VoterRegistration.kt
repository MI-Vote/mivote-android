package io.michiganelections.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VoterRegistration(
  val registered: Boolean,
  val ballot: Boolean?,
  val absentee: Boolean?,
  @Json(name = "absentee_application_received")
  val absenteeApplicationReceived: String?, //date
  @Json(name = "absentee_ballot_sent")
  val absenteeBallotSent: String?, //date
  @Json(name = "absentee_ballot_received")
  val absenteeBallotReceived: String?, //date
  @Json(name = "polling_location")
  val pollingLocation: List<String>?,
  @Json(name = "dropbox_locations")
  val dropboxLocations: List<AddressHours>?,
  @Json(name = "recently_moved")
  val recentlyMoved: Boolean?,
  val precinct: Precinct?,
  val districts: List<District>?
)
