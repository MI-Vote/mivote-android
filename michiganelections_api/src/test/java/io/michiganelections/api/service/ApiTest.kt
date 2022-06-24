package io.michiganelections.api.service

import io.michiganelections.api.model.Election
import io.michiganelections.api.model.ResultPage
import io.michiganelections.api.model.SampleBallot
import io.michiganelections.api.model.VoterRegistration
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.time.LocalDate

@ExperimentalCoroutinesApi
class ApiTest {

  private lateinit var mockRetrofit: Retrofit
  private lateinit var mockEndpoints: Endpoints

  private lateinit var service: ApiService

  @Before
  fun setup() {
    mockRetrofit = mockk()
    mockEndpoints = mockk()
    every { mockRetrofit.create(Endpoints::class.java) } returns mockEndpoints

    service = ApiServiceImpl(
      retrofit = mockRetrofit
    )
  }

  @Test
  fun testGetElections() = runBlockingTest {
    val expected = mockk<ResultPage<Election>>()
    coEvery { mockEndpoints.elections() } returns expected
    Assert.assertEquals(expected, service.getElections())
    coVerify { mockEndpoints.elections() }
  }

  @Test
  fun testGetVoter() = runBlockingTest {
    val firstName = "John"
    val lastName = "Doe"
    val birthDate = LocalDate.of(2020, 11, 3)
    val zipcode = "49501"

    val expected = mockk<VoterRegistration>()

    coEvery { mockEndpoints.registrations(firstName, lastName, "2020-11-03", zipcode) } returns expected

    val actual = service.getVoter(firstName, lastName, birthDate, zipcode)
    Assert.assertEquals(expected, actual)

    coVerify { mockEndpoints.registrations(firstName, lastName, "2020-11-03", zipcode) }
  }

  @Test
  fun testGetSampleBallot() = runBlockingTest {
    val precinctId = 43947
    val expectedBallot = mockk<SampleBallot>()

    coEvery { mockEndpoints.getSampleBallotUrl(precinctId) } returns expectedBallot

    val actualBallot = service.getSampleBallot(precinctId)
    Assert.assertEquals(expectedBallot, actualBallot)

    coVerify { mockEndpoints.getSampleBallotUrl(precinctId) }
  }
}
