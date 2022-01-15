package spiral.bit.dev.imageloader.ui.features.favouritePictures.models

import spiral.bit.dev.imageloader.data.dataSources.remote.models.unsplash.UnsplashPictureItem
import spiral.bit.dev.imageloader.ui.common.adapters.ListItem

data class UnsplashPictureUI(
    val results: List<UnsplashPictureItemUI>
) : ListItem
