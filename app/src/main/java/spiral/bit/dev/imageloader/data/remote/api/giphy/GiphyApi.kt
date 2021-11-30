package spiral.bit.dev.imageloader.data.remote.api.giphy

import spiral.bit.dev.imageloader.data.remote.dto.giphy.Gif
import spiral.bit.dev.imageloader.data.remote.dto.giphy.RandomId

interface GiphyApi {

    suspend fun fetchRandomGif(): Gif

    suspend fun fetchRandomId(): RandomId
}