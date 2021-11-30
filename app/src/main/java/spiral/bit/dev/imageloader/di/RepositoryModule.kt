package spiral.bit.dev.imageloader.di

import org.kodein.di.DI
import org.kodein.di.bindMultiton
import org.kodein.di.bindSingleton
import spiral.bit.dev.imageloader.data.remote.api.giphy.GiphyApiImpl
import spiral.bit.dev.imageloader.data.remote.api.unsplash.UnsplashApiImpl
import spiral.bit.dev.imageloader.data.repositories.GiphyRepository
import spiral.bit.dev.imageloader.data.repositories.UnsplashRepository

val repositoryModule = DI.Module(name = "repositories") {
    bindRepositories()
}

fun DI.Builder.bindRepositories() {
    bindMultiton { giphyApiImpl: GiphyApiImpl -> GiphyRepository(giphyApiImpl) }
    bindMultiton { unsplashApiImpl: UnsplashApiImpl -> UnsplashRepository(unsplashApiImpl) }
}
