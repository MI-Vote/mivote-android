package io.michiganelections.api.injection

import com.squareup.moshi.Moshi
import io.michiganelections.api.service.ApiServiceImpl
import io.mockk.mockk
import okhttp3.OkHttpClient
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ApiModuleTest {

  private lateinit var apiModule: ApiModule

  @Before
  fun setup() {
    apiModule = ApiModule()
  }

  @Test
  fun testProvideMoshi() {
    Assert.assertNotNull(apiModule.provideMoshi())
  }

  @Test
  fun testProvideHttpClient() {
    Assert.assertNotNull(apiModule.provideOkHttpClient())
  }

  @Test
  fun testProvideRetrofit() {
    val mockClient = mockk<OkHttpClient>()
    val mockMoshi = Moshi.Builder().build()

    Assert.assertNotNull(apiModule.provideRetrofit(mockClient, mockMoshi))
  }

  @Test
  fun testProvideApiService() {
    val impl = mockk<ApiServiceImpl>()
    Assert.assertEquals(impl, apiModule.provideApiService(impl))
  }
}
