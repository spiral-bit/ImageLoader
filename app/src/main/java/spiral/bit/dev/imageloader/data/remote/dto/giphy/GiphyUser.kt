package spiral.bit.dev.imageloader.data.remote.dto.giphy

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GiphyUser(
    val avatar_url: String,
    val banner_url: String,
    val profile_url: String,
    val username: String,
    val display_name: String
)