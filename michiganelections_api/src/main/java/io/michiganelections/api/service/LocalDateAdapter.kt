package io.michiganelections.api.service

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateAdapter {
  @ToJson fun toJson(date: LocalDate?): String? {
    return date?.let{ DateTimeFormatter.ISO_DATE.format(it) }
  }

  @FromJson fun fromJson(date: String?): LocalDate? {
    return date?.let { DateTimeFormatter.ISO_DATE.parse(it, LocalDate::from) }
  }
}
