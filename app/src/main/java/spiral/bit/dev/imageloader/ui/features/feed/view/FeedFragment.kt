package spiral.bit.dev.imageloader.ui.features.feed.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.provider
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.FragmentFeedBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding
import spiral.bit.dev.imageloader.ui.common.extensions.toast
import spiral.bit.dev.imageloader.ui.features.feed.adapters.FeedAdapter
import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedBindings
import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedFeature
import spiral.bit.dev.imageloader.ui.features.feed.mvi.event.FeedUiEvent
import spiral.bit.dev.imageloader.ui.features.feed.mvi.viewmodel.FeedViewModel

class FeedFragment : BaseFragment<FragmentFeedBinding, FeedUiEvent, FeedViewModel>(FragmentFeedBinding::inflate), DIAware {

    override val di: DI by closestDI()
    private val feedFeature by provider<FeedFeature>(tag = "feedFeature")
    private val feedBindings by lazy { FeedBindings(this, feedFeature.invoke()) }
    private val feedAdapter = FeedAdapter(onPictureClick = { unsplashPicture ->
        onNext(FeedUiEvent.SavePicture(unsplashPicture))
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProperties()
        setUpRecyclerView()
    }

    private fun initProperties() {
        feedBindings.setup(this)
    }

    private fun setUpRecyclerView() = binding {
        feedRecyclerView.adapter = feedAdapter
    }

    override fun accept(feedViewModel: FeedViewModel) = binding {
        lifecycleScope.launch {
            feedViewModel.error?.let { errorHappens ->
                root.toast(getString(R.string.error_format, errorHappens))
            } ?: run {
                Log.d("FUCK", "IN FRAG ${feedViewModel.pictures}")
                feedAdapter.submitData(feedViewModel.pictures)
            }
        }
    }
}

