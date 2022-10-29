package io.michiganelections.api.service

import android.annotation.SuppressLint
import io.michiganelections.api.model.Ballot
import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.model.VoterRegistration
import retrofit2.Retrofit
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
  retrofit: Retrofit
) : ApiService {
  private val endpoint = retrofit.create(Endpoints::class.java)

  override suspend fun getElections(): ResultPage<Election> {
    return endpoint.elections()
  }

  @SuppressLint("NewApi") // desugared java.time false positive
  override suspend fun getVoter(
    firstName: String,
    lastName: String,
    birthDate: LocalDate,
    zipcode: String
  ): VoterRegistration {
    val birthdayStr = DateTimeFormatter.ISO_DATE.format(birthDate)
    return endpoint.registrations(firstName, lastName, birthdayStr, zipcode)
  }

  override suspend fun getBallots(electionId: Int, precinctId: Int): ResultPage<Ballot> {
    return endpoint.ballots(electionId, precinctId)
  }
}
