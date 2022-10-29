package io.michiganelections.api.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
@JsonClass(generateAdapter = true)
data class Election(
  val id: Int,
  val name: String,
  val date: LocalDate,
  val active: Boolean,
  val referenceUrl: String?
): Parcelable
