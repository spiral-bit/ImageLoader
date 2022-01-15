package spiral.bit.dev.imageloader.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.dataSources.local.dao.OrderBy
import spiral.bit.dev.imageloader.data.dataSources.local.dao.UnsplashDao
import spiral.bit.dev.imageloader.data.dataSources.remote.api.unsplash.UnsplashApi
import spiral.bit.dev.imageloader.data.dataSources.remote.models.unsplash.UnsplashPictureItem
import spiral.bit.dev.imageloader.data.paging.unsplash.UnsplashRemotePagingSource
import spiral.bit.dev.imageloader.ui.common.mappers.UnsplashMapper
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI

class UnsplashRepository(
    private val unsplashDao: UnsplashDao,
    private val unsplashMapper: UnsplashMapper,
    private val pagingConfig: PagingConfig,
    private val unsplashRemotePagingSource: UnsplashRemotePagingSource
) {

    fun fetchPhotos(orderBy: OrderBy): Observable<PagingData<UnsplashPictureItemUI>> {
        unsplashRemotePagingSource.setOrderByValue(orderBy.name)
        return Pager(pagingConfig, pagingSourceFactory = { unsplashRemotePagingSource }).observable
    }

    fun insert(unsplashPictureItemUI: UnsplashPictureItemUI): Completable {
        val unsplashPictureDB = unsplashMapper.toEntityPicture(unsplashPictureItemUI)
        return unsplashDao.insert(unsplashPictureDB)
    }

    fun delete(unsplashPictureItemUI: UnsplashPictureItemUI): Completable {
        return unsplashDao.deletePictureById(unsplashPictureItemUI.id)
    }

    fun fetchAllPictures(): Observable<List<UnsplashPictureItemUI>> {
        val entitiesObservable = unsplashDao.observe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
        return entitiesObservable.map { entitiesList ->
            entitiesList.map { entityModel ->
                unsplashMapper.toUIPicture(entityModel)
            }
        }
    }
}