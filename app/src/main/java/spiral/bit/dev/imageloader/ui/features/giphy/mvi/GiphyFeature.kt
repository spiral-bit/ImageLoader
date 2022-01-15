package spiral.bit.dev.imageloader.ui.features.giphy.mvi

import com.badoo.mvicore.element.Actor
import com.badoo.mvicore.element.Bootstrapper
import com.badoo.mvicore.element.Reducer
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import spiral.bit.dev.imageloader.data.dataSources.remote.models.gif.GifRemote
import spiral.bit.dev.imageloader.data.repositories.GiphyRepository
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyFeature.*
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyFeature.Effect.*

class GiphyFeature(giphyRepository: GiphyRepository) : ActorReducerFeature<Wish, Effect, State, Nothing>(
    initialState = initialState,
    reducer = ReducerImpl(),
    actor = ActorImpl(giphyRepository),
    bootstrapper = BootstrapperImpl()
) {

    data class State(
        val gif: GifRemote?
    )

    sealed class Wish {
        object SaveToFavourites : Wish()
        object LoadNextGif : Wish()
    }

    sealed class Effect {
        data class SuccessfullyFetch(val gif: GifRemote) : Effect()
        data class FailureFetch(val error: Throwable) : Effect()
        data class FailureSave(val error: Throwable) : Effect()
        object GifAlreadySavedError : Effect()
        object SuccessfullySave : Effect()
    }

    class ActorImpl(private val giphyRepository: GiphyRepository) : Actor<State, Wish, Effect> {

        override fun invoke(state: State, wish: Wish): Observable<Effect> = when (wish) {
            Wish.LoadNextGif -> {
                giphyRepository.fetchRandomGif()
                    .map<Effect> { SuccessfullyFetch(it) }
                    .toObservable()
                    .onErrorReturn { FailureFetch(it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
            Wish.SaveToFavourites -> {
                giphyRepository.saveLiked(checkNotNull(state.gif))
                    .toObservable<Effect>()
                    .map<Effect> { SuccessfullySave }
                    .onErrorReturn { GifAlreadySavedError }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }

    class ReducerImpl : Reducer<State, Effect> {
        override fun invoke(state: State, effect: Effect): State = when (effect) {
            is FailureFetch -> state.copy(gif = null)
            is SuccessfullyFetch -> state.copy(gif = effect.gif)
            is SuccessfullySave -> state.copy(gif = state.gif)
            is FailureSave -> state.copy(gif = state.gif)
            is GifAlreadySavedError -> state.copy(gif = state.gif)
        }
    }

    class BootstrapperImpl : Bootstrapper<Wish> {
        override fun invoke(): Observable<Wish> = Observable.just(Wish.LoadNextGif)
    }

    companion object {
        private val initialState = State(
            gif = null
        )
    }
}

