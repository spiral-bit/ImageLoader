package spiral.bit.dev.imageloader.di

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFeature
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesFeature
import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedFeature
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyFeature

val featuresModule = DI.Module(name = "featuresModule") {
    bindFeatures()
}

fun DI.Builder.bindFeatures() {
    bindProvider(tag = "giphyFeature") {
        GiphyFeature(giphyRepository = instance(tag = "giphyRepository"))
    }
    bindProvider(tag = "favouriteGifsFeature") {
        FavouriteGifsFeature(
            giphyRepository = instance(tag = "giphyRepository")
        )
    }
    bindProvider(tag = "favouritePicturesFeature") {
        FavouritePicturesFeature(
            unsplashRepository = instance(tag = "unsplashRepository")
        )
    }
    bindProvider(tag = "feedFeature") {
        FeedFeature(unsplashRepository = instance(tag = "unsplashRepository"))
    }
}
