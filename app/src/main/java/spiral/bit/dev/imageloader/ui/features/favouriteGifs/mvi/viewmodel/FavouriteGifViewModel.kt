package spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.viewmodel

import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.GifUI

data class FavouriteGifViewModel(
    val gifs: List<GifUI>,
    val error: Throwable?,
    val isLoading: Boolean,
    val isOpenDetailsScreen: Boolean
)