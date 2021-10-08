package io.michiganelections.api.service

import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.model.VoterRegistration
import retrofit2.http.GET
import retrofit2.http.Query

internal interface Endpoints {
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
