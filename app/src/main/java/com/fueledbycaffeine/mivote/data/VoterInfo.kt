package com.fueledbycaffeine.mivote.data

import androidx.compose.runtime.Stable
import java.time.LocalDate

@Stable
data class VoterInfo(
  val firstName: String,
  val lastName: String,
  val birthdate: LocalDate?,
  val zipcode: String
)
