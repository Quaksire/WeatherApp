package com.quaksire.network

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by Julio.
 */
class NetworkModuleTest {
    private var mRetrofitModule: NetworkModule? = null

    @Before
    fun setUp() {
        this.mRetrofitModule = NetworkModule("http://test")
    }

    @After
    fun tearDown() {
        this.mRetrofitModule = null
    }

    @Test
    fun canProvideGson() {
        Assert.assertNotNull(this.mRetrofitModule!!.provideGson())
    }

    @Test
    fun canProvideRxJavaCallAdapterFactory() {
        Assert.assertNotNull(this.mRetrofitModule!!.provideRxJavaCallAdapterFactory())
    }

    @Test
    fun canProvideGsonConverterFactory() {
        Assert.assertNotNull(
            this.mRetrofitModule!!.provideGsonConverterFactory(
                this.mRetrofitModule!!.provideGson()
            )
        )
    }

    @Test
    fun canProvideClient() {
        Assert.assertNotNull(this.mRetrofitModule!!.provideClient())
    }

    @Test
    fun canProvideRetrofit() {
        Assert.assertNotNull(
            this.mRetrofitModule!!.provideRetrofit(
                this.mRetrofitModule!!.provideClient(),
                this.mRetrofitModule!!.provideGsonConverterFactory(
                    this.mRetrofitModule!!.provideGson()
                ),
                this.mRetrofitModule!!.provideRxJavaCallAdapterFactory()
            )
        )
    }
}