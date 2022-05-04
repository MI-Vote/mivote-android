package io.michiganelections.api.service

import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.model.SampleBallot
import io.michiganelections.api.model.VoterRegistration
import java.time.LocalDate

interface ApiService {
  suspend fun getElections(): ResultPage<Election>

  suspend fun getVoter(
    firstName: String,
    lastName: String,
    birthDate: LocalDate,
    zipcode: String
  ): VoterRegistration

  suspend fun getSampleBallot(electionId: Int? = null, precinctId: Int? = null): SampleBallot
}
