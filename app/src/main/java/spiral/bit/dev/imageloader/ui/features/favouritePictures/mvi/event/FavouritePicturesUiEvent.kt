package spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.event

import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureUI

sealed class FavouritePicturesUiEvent {
    data class OpenPictures(val unsplashPictureUI: UnsplashPictureItemUI) : FavouritePicturesUiEvent()
    data class DeletePictures(val unsplashPictureUI: UnsplashPictureItemUI) : FavouritePicturesUiEvent()
    object FetchPictures : FavouritePicturesUiEvent()
}