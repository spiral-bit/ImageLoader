package spiral.bit.dev.imageloader.ui.features.feed

import android.os.Bundle
import android.view.View
import spiral.bit.dev.imageloader.databinding.FragmentFeedBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding

class FeedFragment : BaseFragment<FragmentFeedBinding>(FragmentFeedBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpClicks()
    }

    private fun setUpViews() = binding {

    }

    private fun setUpClicks() = binding {

    }
}

