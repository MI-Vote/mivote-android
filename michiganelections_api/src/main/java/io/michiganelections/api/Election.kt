package io.michiganelections.api

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Election(
  val id: Long,
  val name: String,
  val url: String,
  val date: String,
  val description: String,
  val active: Boolean,
  val referenceUrl: String?
)
