package spiral.bit.dev.imageloader.di

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import spiral.bit.dev.imageloader.data.dataSources.local.dao.GiphyDao
import spiral.bit.dev.imageloader.data.dataSources.local.dao.UnsplashDao
import spiral.bit.dev.imageloader.data.dataSources.remote.api.giphy.GiphyApi
import spiral.bit.dev.imageloader.data.dataSources.remote.api.unsplash.UnsplashApi
import spiral.bit.dev.imageloader.data.repositories.GiphyRepository
import spiral.bit.dev.imageloader.data.repositories.UnsplashRepository
import spiral.bit.dev.imageloader.ui.common.mappers.GiphyMapper
import spiral.bit.dev.imageloader.ui.common.mappers.UnsplashMapper

val repositoryModule = DI.Module(name = "repositoryModule") {
    bindRepositories()
    bindRepoParameters()
}

fun DI.Builder.bindRepoParameters() {
    bindSingleton(tag = "giphyRepoParameters") {
        GiphyRepoParameters(instance(tag = "giphyApi"), instance(tag = "giphyDao"), instance(tag = "giphyMapper"))
    }
    bindSingleton(tag = "unsplashRepoParameters") {
        UnsplashRepoParameters(instance(tag = "unsplashApi"), instance(tag = "unsplashDao"), instance(tag = "unsplashMapper"))
    }
}

fun DI.Builder.bindRepositories() {
    bindSingleton(tag = "giphyRepository") {
        val repoParameters = instance<GiphyRepoParameters>(tag = "giphyRepoParameters")
        GiphyRepository(
            repoParameters.giphyApi,
            repoParameters.giphyDao,
            repoParameters.giphyMapper
        )
    }
    bindSingleton(tag = "unsplashRepository") {
        val repoParameters = instance<UnsplashRepoParameters>(tag = "unsplashRepoParameters")
        UnsplashRepository(
            repoParameters.unsplashDao,
            repoParameters.unsplashMapper,
            instance(tag = "pagingConfig"),
            instance(tag = "unsplashPagingSource")
        )
    }
}

data class UnsplashRepoParameters(val unsplashApi: UnsplashApi, val unsplashDao: UnsplashDao, val unsplashMapper: UnsplashMapper)

data class GiphyRepoParameters(val giphyApi: GiphyApi, val giphyDao: GiphyDao, val giphyMapper: GiphyMapper)
