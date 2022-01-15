package spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.event

import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.GifUI


sealed class FavouriteGifUiEvent {
    object LoadGifItems : FavouriteGifUiEvent()
    data class DeleteGifItem(val gifUI: GifUI) : FavouriteGifUiEvent()
    data class OpenGifItem(val gifUI: GifUI) : FavouriteGifUiEvent()
}
