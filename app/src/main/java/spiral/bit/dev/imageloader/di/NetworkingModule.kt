package spiral.bit.dev.imageloader.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import spiral.bit.dev.imageloader.data.dataSources.remote.api.giphy.GiphyApi
import spiral.bit.dev.imageloader.data.dataSources.remote.api.unsplash.UnsplashApi

val networkModule = DI.Module(name = "networkingModule") {
    bindApi()
    bindBaseUrls()
    bindApiKeys()
    bindRetrofits()
    bindClients()
}

fun DI.Builder.bindBaseUrls() {
    bindConstant(tag = "unsplashBaseUrl") { "https://api.unsplash.com/" }
    bindConstant(tag = "giphyBaseUrl") { "https://api.giphy.com/v1/" }
}

fun DI.Builder.bindApiKeys() {
    bindConstant(tag = "unsplashApiKey") { "ouz6zflE2E6dja44N97gqYk1NQMjBUOUtyW6thWpbq8" }
    bindConstant(tag = "giphyApiKey") { "2f3ScvJ19yntXd6b6d8HNkfjWArouMFv" }
}

fun DI.Builder.bindApi() {
    bindSingleton(tag = "unsplashApi") {
        val retrofit = instance<Retrofit>(tag = "unsplashRetrofit")
        retrofit.create(UnsplashApi::class.java)
    }

    bindSingleton(tag = "giphyApi") {
        val retrofit = instance<Retrofit>(tag = "giphyRetrofit")
        retrofit.create(GiphyApi::class.java)
    }
}

fun DI.Builder.bindClients() {
    bindSingleton(tag = "giphyClient") {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.build()
    }

    bindSingleton(tag = "unsplashClient") {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.build()
    }
}

fun DI.Builder.bindRetrofits() {
    bindSingleton(tag = "unsplashRetrofit") {
        val unsplashClient = instance<OkHttpClient>(tag = "unsplashClient")
        val unsplashBaseUrl by di.named.instance<String>()
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(unsplashBaseUrl)
            .client(unsplashClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    bindSingleton(tag = "giphyRetrofit") {
        val giphyClient = instance<OkHttpClient>(tag = "giphyClient")
        val giphyBaseUrl by di.named.instance<String>()
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(giphyBaseUrl)
            .client(giphyClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}