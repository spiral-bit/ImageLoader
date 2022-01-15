package spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import spiral.bit.dev.imageloader.ui.features.favouritePictures.view.FavouritePicturesFragment
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.event.FavouritePicturesUiEventTransformer
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.viewmodel.FavouritePicturesViewModelTransformer

class FavouritePicturesBinding(
    view: FavouritePicturesFragment,
    private val favouritePicturesFeature: FavouritePicturesFeature
) : AndroidBindings<FavouritePicturesFragment>(view) {

    override fun setup(view: FavouritePicturesFragment) {
        binder.bind(favouritePicturesFeature to view using FavouritePicturesViewModelTransformer())
        binder.bind(view to favouritePicturesFeature using FavouritePicturesUiEventTransformer())
    }
}