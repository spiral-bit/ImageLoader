package spiral.bit.dev.imageloader.data.paging.unsplash

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.dataSources.local.dao.OrderBy
import spiral.bit.dev.imageloader.data.dataSources.remote.api.unsplash.UnsplashApi
import spiral.bit.dev.imageloader.ui.common.extensions.toLoadResult
import spiral.bit.dev.imageloader.ui.common.mappers.UnsplashMapper
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI
import java.util.*

class UnsplashRemotePagingSource(
    private val unsplashApi: UnsplashApi,
    private val unsplashMapper: UnsplashMapper
) : RxPagingSource<Int, UnsplashPictureItemUI>() {

    private var orderByValue = OrderBy.POPULAR.name

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, UnsplashPictureItemUI>> {
        val position = params.key ?: UNSPLASH_START_PAGE_INDEX
        return fetchListOfPhotos(position)
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPictureItemUI>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(anchorPosition))
            state.pages.getOrNull(anchorPageIndex.inc())?.prevKey ?: state.pages.getOrNull(anchorPageIndex - 1)?.nextKey
        }
    }

    private fun fetchListOfPhotos(position: Int): Single<LoadResult<Int, UnsplashPictureItemUI>> {
        return unsplashApi.fetchListOfPhotos(pageCount = position, itemsPerPageCount = 8, orderByValue.lowercase(Locale.getDefault()))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map { unsplashPictures ->
                val uiPictures = unsplashPictures.map { unsplashMapper.toUIPicture(it) }
                uiPictures.toLoadResult(position, UNSPLASH_START_PAGE_INDEX)
            }
    }

    fun setOrderByValue(orderByName: String) {
        orderByValue = orderByName
    }

    companion object {
        private const val UNSPLASH_START_PAGE_INDEX = 1
    }
}