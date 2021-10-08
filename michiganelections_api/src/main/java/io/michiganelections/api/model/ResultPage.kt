package io.michiganelections.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultPage<out T>(
  val results: List<T>,
  val count: Int
)
