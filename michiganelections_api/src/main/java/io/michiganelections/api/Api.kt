package io.michiganelections.api

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Api {
  interface Endpoint {
    @GET("elections")
    suspend fun elections(
      @Query("active") isActive: Boolean = true
    ): ResultPage<Election>

    @GET("registrations")
    suspend fun registrations(
      @Query("first_name") firstName: String,
      @Query("last_name") lastName: String,
      @Query("birth_date") birthDate: String,
      @Query("zip_code") zipcode: String
    ): VoterRegistration
  }

  class Service(retrofit: Retrofit) {
    private val endpoint = retrofit.create(Endpoint::class.java)

    suspend fun getElections(): ResultPage<Election> {
      return endpoint.elections()
    }

    suspend fun getVoter(
      firstName: String,
      lastName: String,
      birthDate: LocalDate,
      zipcode: String
    ): VoterRegistration {
      val birthdayStr = DateTimeFormatter.ISO_DATE.format(birthDate)
      return endpoint.registrations(firstName, lastName, birthdayStr, zipcode)
    }
  }
}
