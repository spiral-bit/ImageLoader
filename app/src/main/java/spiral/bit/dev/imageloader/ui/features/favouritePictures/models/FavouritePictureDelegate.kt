package spiral.bit.dev.imageloader.ui.features.favouritePictures.models

import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.ItemFavouritePictureBinding
import spiral.bit.dev.imageloader.ui.common.adapters.ListItem

fun favouritePictureDelegate(
    openDetails: (UnsplashPictureItemUI) -> Unit = {},
    deletePicture: (UnsplashPictureItemUI) -> Unit = {}
) = adapterDelegateViewBinding<UnsplashPictureItemUI, ListItem, ItemFavouritePictureBinding>({ inflater, container ->
    ItemFavouritePictureBinding.inflate(inflater, container, false)
}) {
    with(binding) {
        moreDetailsButton.setOnClickListener {
            openDetails.invoke(item)
        }

        deletePictureButton.setOnClickListener {
            deletePicture.invoke(item)
        }

        bind {
            moreDetailsButton.text = context.getString(R.string.more_details_label)
            pictureImageView.load(item.urls.full) { transformations(RoundedCornersTransformation(32f)) }
            pictureAuthorTextView.text = item.user.username
        }
    }
}