package spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.view.FavouriteGifsFragment
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.event.FavouriteGifUiEventTransformer
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.viewmodel.FavouriteGifViewModelTransformer

class FavouriteGifsFragmentBindings(
    view: FavouriteGifsFragment,
    private val favouriteGifsFeature: FavouriteGifsFeature
) : AndroidBindings<FavouriteGifsFragment>(view) {

    override fun setup(view: FavouriteGifsFragment) {
        binder.bind(favouriteGifsFeature to view using FavouriteGifViewModelTransformer())
        binder.bind(view to favouriteGifsFeature using FavouriteGifUiEventTransformer())
    }
}