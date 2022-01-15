package spiral.bit.dev.imageloader.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.dataSources.local.dao.GiphyDao
import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.GifDB
import spiral.bit.dev.imageloader.data.dataSources.remote.api.giphy.GiphyApi
import spiral.bit.dev.imageloader.data.dataSources.remote.models.gif.GifRemote
import spiral.bit.dev.imageloader.ui.common.mappers.GiphyMapper
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.GifUI

class GiphyRepository(
    private val giphyApi: GiphyApi,
    private val giphyDao: GiphyDao,
    private val giphyMapper: GiphyMapper
) {

    fun fetchRandomGif(): Single<GifRemote> {
        return giphyApi.fetchRandomGif()
    }

    fun saveLiked(gifRemote: GifRemote): Completable {
        val gifEntity = giphyMapper.toEntityGif(gifRemote)
        return giphyDao.insert(gifEntity)
    }

    fun deleteGifById(gifUI: GifUI): Completable {
        return giphyDao.deleteGifById(gifUI.dataUI.id)
    }

    fun fetchAllGifs(): Observable<List<GifUI>> {
        val entitiesObservable = giphyDao.observe()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
        return entitiesObservable.map { entitiesList ->
            entitiesList.map { entityModel ->
                giphyMapper.toUIGif(entityModel)
            }
        }
    }
}