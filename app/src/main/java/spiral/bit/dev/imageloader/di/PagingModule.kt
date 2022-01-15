package spiral.bit.dev.imageloader.di

import androidx.paging.PagingConfig
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance
import spiral.bit.dev.imageloader.data.paging.unsplash.UnsplashRemotePagingSource

val pagingModule = DI.Module(name = "pagingModule") {
    bindPagingConfig()
    bindPagingSources()
}

fun DI.Builder.bindPagingSources() {
    bindProvider(tag = "unsplashPagingSource") {
        UnsplashRemotePagingSource(
            unsplashApi = instance(tag = "unsplashApi"),
            unsplashMapper = instance(tag = "unsplashMapper")
        )
    }
}

fun DI.Builder.bindPagingConfig() {
    bindProvider(tag = "pagingConfig") {
        PagingConfig(
            pageSize = 1,
            prefetchDistance = 2,
            enablePlaceholders = true,
            initialLoadSize = 3,
            maxSize = 14
        )
    }
}