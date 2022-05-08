package io.michiganelections.api.service

import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.model.SampleBallot
import io.michiganelections.api.model.VoterRegistration
import retrofit2.Retrofit
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
  retrofit: Retrofit
) : ApiService {
  private val endpoint = retrofit.create(Endpoints::class.java)

  override suspend fun getElections(
    isActive: Boolean,
    limit: Int,
    offset: Int): ResultPage<Election> {
    return endpoint.elections(isActive, limit, offset)
  }

  override suspend fun getVoter(
    firstName: String,
    lastName: String,
    birthDate: LocalDate,
    zipcode: String
  ): VoterRegistration {
    val birthdayStr = DateTimeFormatter.ISO_DATE.format(birthDate)
    return endpoint.registrations(firstName, lastName, birthdayStr, zipcode)
  }

  override suspend fun getSampleBallot(electionId: Int?, precinctId: Int?): SampleBallot {
    return endpoint.getSampleBallotUrl(electionId, precinctId)
  }
}
