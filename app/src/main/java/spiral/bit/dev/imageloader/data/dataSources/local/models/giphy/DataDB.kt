package spiral.bit.dev.imageloader.data.dataSources.local.models.giphy

import androidx.room.Embedded
import spiral.bit.dev.imageloader.data.dataSources.remote.models.gif.User

data class DataDB(
    val type: String,
    val id: String,
    val slug: String,
    val url: String,
    val cutUrl: String,
    val bitlyGifUrl: String,
    val embedUrl: String,
    val username: String,
    val source: String,
    val rating: String,
    val contentUrl: String,
    @Embedded val user: User,
    val sourceTld: String,
    val sourcePostUrl: String,
    val isSticker: Int,
    val importDatetime: String,
    val trendingDatetime: String,
    @Embedded val images: ImagesDB,
    val title: String,
)
