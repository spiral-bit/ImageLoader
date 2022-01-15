package spiral.bit.dev.imageloader.ui.features.giphy.view

import android.os.Bundle
import android.view.View
import coil.Coil
import coil.ImageLoader
import coil.load
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance
import org.kodein.di.provider
import spiral.bit.dev.imageloader.databinding.FragmentGiphyBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyBindings
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyFeature
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.event.GiphyUiEvent
import spiral.bit.dev.imageloader.ui.features.giphy.mvi.viewmodel.GiphyViewModel

class GiphyFragment : BaseFragment<FragmentGiphyBinding, GiphyUiEvent, GiphyViewModel>(FragmentGiphyBinding::inflate), DIAware {

    override val di: DI by closestDI()
    private val giphyFeature by provider<GiphyFeature>(tag = "giphyFeature")
    private val giphyBindings by lazy { GiphyBindings(this, giphyFeature.invoke()) }
    private val coilGifLoader by instance<ImageLoader>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProperties()
        setUpClicks()
    }

    private fun initProperties() {
        giphyBindings.setup(this)
        Coil.setImageLoader(coilGifLoader)
    }

    private fun setUpClicks() = binding {
        iconLikeImageView.setOnClickListener {
            onNext(GiphyUiEvent.LikeClicked)
        }

        iconNextImageView.setOnClickListener {
            onNext(GiphyUiEvent.NextGifClicked)
        }
    }

    override fun accept(giphyViewModel: GiphyViewModel) = binding {
        val url = giphyViewModel.gifDTO?.dataRemote?.images?.fixedHeight?.fixedHeightUrl
        gifImageView.load(url) {
            crossfade(true)
            crossfade(400)
        }
    }
}
