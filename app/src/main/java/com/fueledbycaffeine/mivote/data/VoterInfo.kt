package com.fueledbycaffeine.mivote.data

import androidx.compose.runtime.Stable
import java.time.LocalDate

@Stable
data class VoterInfo(
  var firstName: String,
  var lastName: String,
  var birthdate: LocalDate,
  var zipcode: String
) {
  fun isValid(): Boolean = firstName.isNotBlank() && lastName.isNotBlank() && zipcode.isNotBlank()
}
