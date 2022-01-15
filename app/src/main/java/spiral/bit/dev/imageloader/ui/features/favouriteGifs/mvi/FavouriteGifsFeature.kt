package spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.repositories.GiphyRepository
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.GifUI
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFeature.*
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFeature.Effect.*

class FavouriteGifsFeature(
    giphyRepository: GiphyRepository
) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = initialState,
    actor = ActorImpl(giphyRepository),
    reducer = ReducerImpl(),
    bootstrapper = BootstrapperImpl()
) {

    class ActorImpl(private val giphyRepository: GiphyRepository) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            is Wish.FetchFavouriteGifs -> {
                giphyRepository.fetchAllGifs()
                    .map<Effect> { SuccessfullyFetchGifs(it) }
                    .onErrorReturn { FailureFetchGifs(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            is Wish.DeleteFavouriteGif -> {
                giphyRepository.deleteGifById(wish.gifUI)
                    .toObservable<Effect>()
                    .map<Effect> { SuccessfullyDeletedGif(state.gifs - wish.gifUI) }
                    .onErrorReturn { FailureDeletedGif(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            is Wish.OpenFavouriteGif -> {
                Observable.just(OpenGif(wish.gifUI))
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is FailureFetchGifs -> State(error = effect.throwable, gifs = emptyList(), isLoading = false, isOpenDetailsScreen = false)
            is SuccessfullyFetchGifs -> State(error = null, gifs = effect.gifList, isLoading = false, isOpenDetailsScreen = false)
            is SuccessfullyDeletedGif -> State(error = null, gifs = effect.gifs, isLoading = false, isOpenDetailsScreen = false)
            is FailureDeletedGif -> State(error = effect.error, gifs = state.gifs, isLoading = false, isOpenDetailsScreen = false)
            is OpenGif -> State(error = null, gifs = state.gifs, isLoading = false, isOpenDetailsScreen = true)
        }
    }

    class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Observable.just(Wish.FetchFavouriteGifs)
    }

    sealed class Wish {
        data class OpenFavouriteGif(val gifUI: GifUI) : Wish()
        data class DeleteFavouriteGif(val gifUI: GifUI) : Wish()
        object FetchFavouriteGifs : Wish()
    }

    sealed class Effect {
        data class SuccessfullyFetchGifs(val gifList: List<GifUI>) : Effect()
        data class FailureFetchGifs(val throwable: Throwable) : Effect()
        data class SuccessfullyDeletedGif(val gifs: List<GifUI>) : Effect()
        data class FailureDeletedGif(val error: Throwable) : Effect()
        data class OpenGif(val gifUI: GifUI) : Effect()
    }

    data class State(
        val gifs: List<GifUI>,
        val error: Throwable?,
        val isLoading: Boolean,
        val isOpenDetailsScreen: Boolean
    )

    companion object {
        private val initialState = State(
            gifs = emptyList(),
            error = null,
            isLoading = false,
            isOpenDetailsScreen = false
        )
    }
}