package spiral.bit.dev.imageloader.ui.features.feed.mvi.event

import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI

sealed class FeedUiEvent {
    object GetPictures : FeedUiEvent()
    data class SavePicture(val unsplashPictureUI: UnsplashPictureItemUI) : FeedUiEvent()
}