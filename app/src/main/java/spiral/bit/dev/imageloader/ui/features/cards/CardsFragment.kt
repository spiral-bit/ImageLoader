package spiral.bit.dev.imageloader.ui.features.cards

import android.os.Bundle
import android.view.View
import spiral.bit.dev.imageloader.databinding.FragmentCardsBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding

class CardsFragment : BaseFragment<FragmentCardsBinding>(FragmentCardsBinding::inflate) {

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