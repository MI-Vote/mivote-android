package com.fueledbycaffeine.mivote.ui.voter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fueledbycaffeine.mivote.data.VoterDataStore
import com.fueledbycaffeine.mivote.data.VoterInfo
import io.michiganelections.api.model.SampleBallot
import io.michiganelections.api.model.VoterRegistration
import io.michiganelections.api.service.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@ExperimentalCoroutinesApi
class VoterRegistrationViewModelTest {

  @Rule @JvmField val testRule = InstantTaskExecutorRule()

  private lateinit var mockApiService: ApiService
  private lateinit var mockVoterDataStore: VoterDataStore
  private lateinit var viewModel: VoterRegistrationViewModel

  @Before
  fun setup() {
    mockApiService = mockk()
    mockVoterDataStore = mockk(relaxed = true)
//    val expectedVoterInfoFromStore = mockk<VoterInfo>()
//    every {
//      mockVoterDataStore.voterInfo
//    } returns expectedVoterInfoFromStore

    viewModel = VoterRegistrationViewModel(
      api = mockApiService,
      dataStore = mockVoterDataStore
    )
  }

  @Test
  fun testCheckRegistration() = runTest {
    val firstName = "John"
    val lastName = "Doe"
    val birthdate = LocalDate.now()
    val zipcode = "12345"
    val voterInfo = VoterInfo(
      firstName = firstName,
      lastName = lastName,
      birthdate = birthdate,
      zipcode = zipcode
    )

    val expectedResult = mockk<VoterRegistration>()

    coEvery {
      mockApiService.getVoter(
        firstName,
        lastName,
        birthdate,
        zipcode
      )
    } returns expectedResult

    viewModel.checkRegistration(voterInfo)
    Assert.assertEquals(expectedResult, viewModel.registration.value)
    coVerify { mockApiService.getVoter(firstName, lastName, birthdate, zipcode) }
  }

  @Test
  fun testGetSampleBallot() = runTest {
    val electionId = 1
    val precinctId = 1
    val expected = mockk<SampleBallot>()

    coEvery { mockApiService.getSampleBallot(electionId, precinctId) } returns expected

    val actual = viewModel.getSampleBallot(electionId, precinctId)
    Assert.assertEquals(expected, actual)

    coVerify { mockApiService.getSampleBallot(electionId, precinctId) }
  }
}
