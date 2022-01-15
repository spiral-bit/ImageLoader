package spiral.bit.dev.imageloader.ui.features.giphy.mvi.event

sealed class GiphyUiEvent {
    object LikeClicked : GiphyUiEvent()
    object NextGifClicked : GiphyUiEvent()
}