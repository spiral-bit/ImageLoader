package spiral.bit.dev.imageloader.data.dataSources.remote.api.giphy

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import spiral.bit.dev.imageloader.BuildConfig
import spiral.bit.dev.imageloader.data.dataSources.remote.models.gif.GifRemote

interface GiphyApi {

    @GET("gifs/random")
    fun fetchRandomGif(@Query("api_key") apiKey: String = API_KEY): Single<GifRemote>

    companion object {
        private const val API_KEY = BuildConfig.GIPHY_API_KEY
    }
}