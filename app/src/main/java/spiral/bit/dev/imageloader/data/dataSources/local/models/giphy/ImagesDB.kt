package spiral.bit.dev.imageloader.data.dataSources.local.models.giphy

import androidx.room.Embedded
import spiral.bit.dev.imageloader.data.dataSources.remote.models.gif.FixedHeight

data class ImagesDB(
    @Embedded val fixedHeight: FixedHeight
)