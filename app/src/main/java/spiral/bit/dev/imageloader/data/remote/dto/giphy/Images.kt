package spiral.bit.dev.imageloader.data.remote.dto.giphy

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class Images(val fixedWidth: FixedWidth)