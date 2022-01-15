package spiral.bit.dev.imageloader.di

import org.kodein.di.DI
import org.kodein.di.bindProvider
import spiral.bit.dev.imageloader.ui.common.mappers.GiphyMapper
import spiral.bit.dev.imageloader.ui.common.mappers.UnsplashMapper

val commonModule = DI.Module(name = "commonModule") {
    bindMappers()
}

fun DI.Builder.bindMappers() {
    bindProvider(tag = "giphyMapper") { GiphyMapper() }
    bindProvider(tag = "unsplashMapper") { UnsplashMapper() }
}