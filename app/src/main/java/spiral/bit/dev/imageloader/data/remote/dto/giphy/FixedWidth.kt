package spiral.bit.dev.imageloader.data.remote.dto.giphy

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class FixedWidth(
    val url: String,
    val width: String,
    val height: String,
    val size: String,
    val mp4: String,
    val mp4_size: String,
    val webp: String,
    val webp_size: String
)

