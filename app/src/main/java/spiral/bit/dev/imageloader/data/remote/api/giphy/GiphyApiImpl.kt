package spiral.bit.dev.imageloader.data.remote.api.giphy

import io.ktor.client.*
import io.ktor.client.request.*
import org.kodein.di.DI
import org.kodein.di.instance
import spiral.bit.dev.imageloader.data.remote.dto.giphy.Gif
import spiral.bit.dev.imageloader.data.remote.dto.giphy.RandomId

class GiphyApiImpl(di: DI) : GiphyApi {

    private val giphyClient by di.instance<HttpClient>(tag = "giphy_client")
    private val randomGifEndpoint by di.instance<String>(tag = "giphy_random_gif_endpoint")
    private val randomIdEndpoint by di.instance<String>(tag = "giphy_random_id_endpoint")
    private val giphyApiKey by di.instance<String>(tag = "giphy_api_key")

    override suspend fun fetchRandomGif(): Gif {
        return giphyClient.get(randomGifEndpoint) {
            parameter("api_key", giphyApiKey)
            val randomId = fetchRandomId()
            parameter("random_id", randomId)
        }
    }

    override suspend fun fetchRandomId(): RandomId {
        return giphyClient.get(randomIdEndpoint) {
            parameter("api_key", giphyApiKey)
        }
    }
}