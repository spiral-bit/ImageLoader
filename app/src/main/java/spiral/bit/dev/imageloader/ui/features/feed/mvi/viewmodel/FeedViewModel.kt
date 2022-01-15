package spiral.bit.dev.imageloader.ui.features.feed.mvi.viewmodel

import androidx.paging.PagingData
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI

data class FeedViewModel(
    val pictures: PagingData<UnsplashPictureItemUI>,
    val error: Throwable?,
    val isLoading: Boolean
)
