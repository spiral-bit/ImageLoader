package spiral.bit.dev.imageloader.data.remote.dto.giphy

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Gif(
    val type: String,
    val id: String,
    val slug: String,
    val url: String,
    val bitly_url: String,
    val embed_url: String,
    val username: String,
    val source: String,
    val rating: String,
    val content_url: String,
    val user: GiphyUser,
    val source_tld: String,
    val source_post_url: String,
    val update_datetime: String,
    val create_datetime: String,
    val import_datetime: String,
    val trending_datetime: String,
    val images: Images,
    val title: String
)


