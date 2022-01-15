package spiral.bit.dev.imageloader.ui.features.giphy.mvi

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import spiral.bit.dev.imageloader.ui.features.giphy.view.GiphyFragment
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.event.GifUiEventTransformer
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.viewmodel.GifViewModelTransformer

class GiphyBindings(
    view: GiphyFragment,
    private val giphyFeature: GiphyFeature
) : AndroidBindings<GiphyFragment>(view) {

    override fun setup(view: GiphyFragment) {
        binder.bind(giphyFeature to view using GifViewModelTransformer())
        binder.bind(view to giphyFeature using GifUiEventTransformer())
    }
}
