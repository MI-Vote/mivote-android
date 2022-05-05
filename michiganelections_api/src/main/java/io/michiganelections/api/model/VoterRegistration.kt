package io.michiganelections.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class VoterRegistration(
  val registered: Boolean,
  val ballot: Boolean?,
  val absentee: Boolean?,
  @Json(name = "absentee_application_received")
  val absenteeApplicationReceived: LocalDate?,
  @Json(name = "absentee_ballot_sent")
  val absenteeBallotSent: LocalDate?,
  @Json(name = "absentee_ballot_received")
  val absenteeBallotReceived: LocalDate?,
  @Json(name = "polling_location")
  val pollingLocation: List<String>?,
  @Json(name = "dropbox_locations")
  val dropboxLocations: List<AddressHours>?,
  @Json(name = "recently_moved")
  val recentlyMoved: Boolean?,
  val precinct: Precinct?,
  val districts: List<District>
) {
  enum class AbsenteeProcessState {
    NOT_STARTED,
    REQUESTED_BALLOT,
    BALLOT_SENT_BY_CLERK,
    BALLOT_RECEIVED_BY_CLERK,
    ;
  }

  val absenteeProcessState get() =
    if (absenteeApplicationReceived != null && absenteeBallotSent != null && absenteeBallotReceived != null) {
      AbsenteeProcessState.BALLOT_RECEIVED_BY_CLERK
    } else if (absenteeApplicationReceived != null && absenteeBallotSent != null) {
      AbsenteeProcessState.BALLOT_SENT_BY_CLERK
    } else if (absenteeApplicationReceived != null) {
      AbsenteeProcessState.REQUESTED_BALLOT
    } else {
      AbsenteeProcessState.NOT_STARTED
    }
}
