package com.quaksire.weatherapp

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.quaksire.weatherapp.utils.ResourcesUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Julio.
 */
@RunWith(JUnit4::class)
abstract class BaseTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        this.mockWebServer = MockWebServer()
        this.mockWebServer.start()

        Constants.weatherUrl = this.mockWebServer.url("/").toString()
    }

    @After
    fun tearDown() {
        this.mockWebServer.shutdown()
    }

    @Throws(Exception::class)
    internal fun setResponse(response: Int, fileName: String) {
        this.mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(response)
                .setBody(
                    ResourcesUtils.getStringFromFile(
                        getInstrumentation().context,
                        fileName
                    )
                )
        )
    }
}