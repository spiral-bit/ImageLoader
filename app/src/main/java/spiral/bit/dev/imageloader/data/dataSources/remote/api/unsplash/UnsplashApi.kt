package spiral.bit.dev.imageloader.data.dataSources.remote.api.unsplash

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import spiral.bit.dev.imageloader.BuildConfig
import spiral.bit.dev.imageloader.data.dataSources.remote.models.unsplash.UnsplashPictureItem

interface UnsplashApi {

    @GET("photos")
    fun fetchListOfPhotos(
        @Query("page") pageCount: Int,
        @Query("per_page") itemsPerPageCount: Int,
        @Query("order_by") orderByValue: String,
        @Query("client_id") apiKey: String = API_KEY
    ): Single<List<UnsplashPictureItem>>

    companion object {
         const val API_KEY = BuildConfig.UNSPLASH_API_KEY
    }
}