package spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.repositories.UnsplashRepository
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesFeature.*
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesFeature.Effect.*

class FavouritePicturesFeature(
    unsplashRepository: UnsplashRepository
) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = initialState,
    actor = ActorImpl(unsplashRepository),
    reducer = ReducerImpl(),
    bootstrapper = BootstrapperImpl()
) {

    class ActorImpl(private val unsplashRepository: UnsplashRepository) : Actor<State, Wish, Effect> {
        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            is Wish.FetchFavouritePictures -> {
                unsplashRepository.fetchAllPictures()
                    .map<Effect> { SuccessfullyObservePictures(it) }
                    .onErrorReturn { FailureFetchPictures(it) }
                    .startWith(LoadingPictures)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            is Wish.DeletePicture -> {
                unsplashRepository.delete(wish.unsplashPictureUI)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable<Effect>()
                    .map<Effect> { SuccessfullyDeletedPicture(state.pictures - wish.unsplashPictureUI) }
                    .onErrorReturn { FailureDeletePictures(it) }
            }
            is Wish.OpenPicture -> {
                Observable.just(OpenPicture(wish.unsplashPictureUI))
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is FailureFetchPictures -> State(isOpenDetailsScreen = false, error = effect.throwable, pictures = emptyList(), isLoading = false)
            is LoadingPictures -> State(isOpenDetailsScreen = false, error = null, pictures = emptyList(), isLoading = true)
            is SuccessfullyObservePictures -> State(isOpenDetailsScreen = false, error = null, pictures = effect.items, isLoading = false)
            is SuccessfullyDeletedPicture -> State(isOpenDetailsScreen = false, error = null, pictures = effect.newItems, isLoading = false)
            is FailureDeletePictures -> State(isOpenDetailsScreen = false, error = effect.throwable, pictures = state.pictures, isLoading = false)
            is OpenPicture -> State(isOpenDetailsScreen = true, error = null, pictures = state.pictures, isLoading = false)
        }
    }

    class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Observable.just(Wish.FetchFavouritePictures)
    }

    sealed class Wish {
        data class OpenPicture(val unsplashPictureUI: UnsplashPictureItemUI) : Wish()
        data class DeletePicture(val unsplashPictureUI: UnsplashPictureItemUI) : Wish()
        object FetchFavouritePictures : Wish()
    }

    sealed class Effect {
        data class SuccessfullyObservePictures(val items: List<UnsplashPictureItemUI>) : Effect()
        data class SuccessfullyDeletedPicture(val newItems: List<UnsplashPictureItemUI>) : Effect()
        data class OpenPicture(val picture: UnsplashPictureItemUI) : Effect()
        data class FailureFetchPictures(val throwable: Throwable) : Effect()
        data class FailureDeletePictures(val throwable: Throwable) : Effect()
        object LoadingPictures : Effect()
    }

    data class State(
        val pictures: List<UnsplashPictureItemUI>,
        val error: Throwable?,
        val isLoading: Boolean,
        val isOpenDetailsScreen: Boolean
    )

    companion object {
        private val initialState = State(
            pictures = emptyList(),
            error = null,
            isLoading = false,
            isOpenDetailsScreen = false
        )
    }
}