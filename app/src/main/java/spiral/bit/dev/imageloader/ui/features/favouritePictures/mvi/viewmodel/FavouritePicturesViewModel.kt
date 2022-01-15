package spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.viewmodel

import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI

data class FavouritePicturesViewModel(
    val pictures: List<UnsplashPictureItemUI>,
    val error: Throwable?,
    val isLoading: Boolean
)
