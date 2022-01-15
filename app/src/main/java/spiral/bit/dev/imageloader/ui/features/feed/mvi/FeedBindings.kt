package spiral.bit.dev.imageloader.ui.features.feed.mvi

import com.badoo.binder.using
import com.badoo.mvicore.android.AndroidBindings
import spiral.bit.dev.imageloader.ui.features.feed.mvi.event.FeedUiEventTransformer
import spiral.bit.dev.imageloader.ui.features.feed.mvi.viewmodel.FeedViewModelTransformer
import spiral.bit.dev.imageloader.ui.features.feed.view.FeedFragment

class FeedBindings(
    view: FeedFragment,
    private val feedFeature: FeedFeature
) : AndroidBindings<FeedFragment>(view) {

    override fun setup(view: FeedFragment) {
        binder.apply {
            bind(feedFeature to view using FeedViewModelTransformer())
            bind(view to feedFeature using FeedUiEventTransformer())
        }
    }
}