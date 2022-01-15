package spiral.bit.dev.imageloader.ui.features.favouriteGifs.models

import coil.load
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import spiral.bit.dev.imageloader.databinding.ItemFavouriteGifBinding
import spiral.bit.dev.imageloader.ui.common.adapters.ListItem

fun favouriteGifDelegate(onItemOpen: (GifUI) -> Unit, onItemDelete: (GifUI) -> Unit) =
    adapterDelegateViewBinding<GifUI, ListItem, ItemFavouriteGifBinding>({ inflater, container ->
        ItemFavouriteGifBinding.inflate(inflater, container, false)
    }) {
        with(binding) {
            moreDetailsButton.setOnClickListener {
                onItemOpen.invoke(item)
            }

            deleteGifButton.setOnClickListener {
                onItemDelete.invoke(item)
            }

            bind {
                gifImageView.load(item.dataUI.images.fixedHeight.fixedHeightUrl)
                gifTitleTextView.text = item.dataUI.title
            }
        }
    }