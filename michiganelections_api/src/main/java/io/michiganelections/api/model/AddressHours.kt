package io.michiganelections.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddressHours(
  val address: List<String>,
  val hours: List<String>
)
