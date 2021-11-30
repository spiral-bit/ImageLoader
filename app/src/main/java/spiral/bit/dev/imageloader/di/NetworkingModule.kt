package spiral.bit.dev.imageloader.di

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.kodein.di.DI
import org.kodein.di.bindConstant
import org.kodein.di.bindMultiton

val networkModule = DI.Module(name = "networking") {
    bindApiKeys()
    bindUnsplashEndpoints()
    bindGiphyEndpoints()
    bindUnsplashClient()
    bindGiphyClient()
}

fun DI.Builder.bindGiphyEndpoints() {
    bindConstant(tag = "giphy_random_gif_endpoint") { "api.giphy.com/v1/gifs/random" }
    bindConstant(tag = "giphy_random_id_endpoint") { "api.giphy.com/v1/randomid" }
}

fun DI.Builder.bindUnsplashEndpoints() {
    bindConstant(tag = "unsplash_random_endpoint") { "https://api.unsplash.com/photos/random" }
    bindConstant(tag = "unsplash_list_photos_endpoint") { "https://api.unsplash.com/photos" }
}

fun DI.Builder.bindApiKeys() {
    bindConstant(tag = "unsplash_api_key") { "ouz6zflE2E6dja44N97gqYk1NQMjBUOUtyW6thWpbq8" }
    bindConstant(tag = "giphy_api_key") { "2f3ScvJ19yntXd6b6d8HNkfjWArouMFv" }
}

fun DI.Builder.bindUnsplashClient() {
    bindMultiton(tag = "unsplash_client") { engine: HttpClientEngine ->
        HttpClient(engine) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.HEADERS
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                })
            }
        }
    }
}

fun DI.Builder.bindGiphyClient() {
    bindMultiton(tag = "giphy_client") { engine: HttpClientEngine ->
        HttpClient(engine) {
            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.HEADERS
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                })
            }
        }
    }
}