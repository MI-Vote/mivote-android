package io.michiganelections.api.service

import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.model.SampleBallot
import io.michiganelections.api.model.VoterRegistration
import retrofit2.http.Query
import java.time.LocalDate

interface ApiService {
  suspend fun getElections(
    isActive: Boolean = true,
    limit: Int = 25,
    offset: Int = 0): ResultPage<Election>

  suspend fun getVoter(
    firstName: String,
    lastName: String,
    birthDate: LocalDate,
    zipcode: String
  ): VoterRegistration

  suspend fun getSampleBallot(electionId: Int? = null, precinctId: Int? = null): SampleBallot
}
