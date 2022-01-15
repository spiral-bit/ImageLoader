package spiral.bit.dev.imageloader.ui.features.favouriteGifs.models

import spiral.bit.dev.imageloader.ui.common.adapters.ListItem

data class GifUI(
    val primaryKeyId: String,
    val dataUI: DataUI,
    val metaUI: MetaUI
) : ListItem