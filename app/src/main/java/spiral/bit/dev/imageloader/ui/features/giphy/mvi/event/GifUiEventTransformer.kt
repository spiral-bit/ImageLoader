package spiral.bit.dev.imageloader.ui.features.giphy.mvi.event

import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyFeature

class GifUiEventTransformer : (GiphyUiEvent) -> GiphyFeature.Wish? {
    override fun invoke(uiEvent: GiphyUiEvent) = when (uiEvent) {
        GiphyUiEvent.LikeClicked -> GiphyFeature.Wish.SaveToFavourites
        GiphyUiEvent.NextGifClicked -> GiphyFeature.Wish.LoadNextGif
    }
}