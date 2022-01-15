package spiral.bit.dev.imageloader.ui.features.feed.mvi

import androidx.paging.PagingData
import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.dataSources.local.dao.OrderBy
import spiral.bit.dev.imageloader.data.repositories.UnsplashRepository
import spiral.bit.dev.imageloader.ui.common.paging.emptyPagingData
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI
import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedFeature.*
import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedFeature.Effect.*

class FeedFeature(
    unsplashRepository: UnsplashRepository
) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = initialState,
    actor = ActorImpl(unsplashRepository),
    reducer = ReducerImpl(),
    bootstrapper = BootstrapperImpl(),
) {

    sealed class Wish {
        object FetchPictures : Wish()
        data class SavePicture(val unsplashPictureUI: UnsplashPictureItemUI) : Wish()
    }

    sealed class Effect {
        data class SuccessfullyFetchPictures(val pictures: PagingData<UnsplashPictureItemUI>) : Effect()
        data class FailureFetchPictures(val throwable: Throwable) : Effect()
        object LoadPictures : Effect()
        object SuccessfullySaved : Effect()
        data class FailureSaved(val error: Throwable) : Effect()
    }

    data class State(
        val pictures: PagingData<UnsplashPictureItemUI>,
        val isLoading: Boolean,
        val error: Throwable?
    )

    class ActorImpl(private val unsplashRepository: UnsplashRepository) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            Wish.FetchPictures -> {
                unsplashRepository.fetchPhotos(OrderBy.POPULAR)
                    .map<Effect> { SuccessfullyFetchPictures(it) }
                    .onErrorReturn { FailureFetchPictures(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            is Wish.SavePicture -> {
                unsplashRepository.insert(wish.unsplashPictureUI)
                    .toObservable<Effect>()
                    .map<Effect> { SuccessfullySaved }
                    .onErrorReturn { FailureSaved(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is FailureFetchPictures -> State(pictures = emptyPagingData(), isLoading = false, error = effect.throwable)
            is LoadPictures -> State(pictures = emptyPagingData(), isLoading = true, error = null)
            is SuccessfullyFetchPictures -> State(pictures = effect.pictures, isLoading = false, error = null)
            is SuccessfullySaved -> State(pictures = state.pictures, isLoading = false, error = null)
            is FailureSaved -> State(pictures = state.pictures, isLoading = false, error = effect.error)
        }
    }

    class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Observable.just(Wish.FetchPictures)
    }

    companion object {
        private val initialState = State(
            pictures = emptyPagingData(),
            isLoading = false,
            error = null
        )
    }
}