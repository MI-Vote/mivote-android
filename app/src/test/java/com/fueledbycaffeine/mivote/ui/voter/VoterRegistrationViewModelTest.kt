package com.fueledbycaffeine.mivote.ui.voter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.fueledbycaffeine.mivote.data.VoterInfo
import com.fueledbycaffeine.mivote.util.evaluateLiveDataSequence
import io.michiganelections.api.model.VoterRegistration
import io.michiganelections.api.service.ApiService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

@ExperimentalCoroutinesApi
class VoterRegistrationViewModelTest {

  @Rule @JvmField val testRule = InstantTaskExecutorRule()

  private lateinit var mockApiService: ApiService
  private lateinit var viewModel: VoterRegistrationViewModel

  @Before
  fun setup() {
    mockApiService = mockk()
    viewModel = VoterRegistrationViewModel(
      api = mockApiService
    )
  }

  @Test
  fun testCheckRegistration() = runBlockingTest {
    val firstName = "John"
    val lastName = "Doe"
    val birthdate = LocalDate.now()
    val zipcode = "12345"
    val expectedResult = mockk<VoterRegistration>()

    coEvery { mockApiService.getVoter(firstName, lastName, birthdate, zipcode) } returns expectedResult

    evaluateLiveDataSequence(
      liveData = viewModel.registrationLiveData,
      evaluators = listOf { registration ->
        Assert.assertEquals(expectedResult, registration)
      },
      block = {
        val voterInfo = VoterInfo(
          firstName = firstName,
          lastName = lastName,
          birthdate = birthdate,
          zipcode = zipcode
        )
        Assert.assertEquals(expectedResult, viewModel.checkRegistration(voterInfo))
      }
    )

    coVerify { mockApiService.getVoter(firstName, lastName, birthdate, zipcode) }
  }
}
