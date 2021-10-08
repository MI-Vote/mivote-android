package io.michiganelections.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class District(
  val id: Long,
  val url: String,
  val name: String,
  val category: String
)
