package com.fueledbycaffeine.mivote.data

import java.time.LocalDate

data class VoterRegistrationSummary(
  val ballotPreviewAvailable: Boolean,
  val absenteeApplicationReceived: LocalDate?,
  val absenteeBallotSent: LocalDate?,
  val absenteeBallotReceived: LocalDate?,
)
