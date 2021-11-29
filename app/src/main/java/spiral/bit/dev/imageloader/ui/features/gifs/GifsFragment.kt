package spiral.bit.dev.imageloader.ui.features.gifs

import android.os.Bundle
import android.view.View
import spiral.bit.dev.imageloader.databinding.FragmentGifsBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding

class GifsFragment : BaseFragment<FragmentGifsBinding>(FragmentGifsBinding::inflate) {

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