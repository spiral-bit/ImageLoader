package spiral.bit.dev.imageloader.ui.features.favourites.view

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.FragmentFavouritesBinding
import spiral.bit.dev.imageloader.ui.base.BindingFragment
import spiral.bit.dev.imageloader.ui.base.binding
import spiral.bit.dev.imageloader.ui.features.favourites.adapters.FavouritesPagerAdapter

class FavouritesFragment : BindingFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {

    private val favouritesViewPagerAdapter by lazy { FavouritesPagerAdapter(requireActivity()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    private fun setUpViewPager() = binding {
        favouritesViewPager.adapter = favouritesViewPagerAdapter
        TabLayoutMediator(favouritesTabLayout, favouritesViewPager) { tab, position ->
            val tabTitle = if (position == 0) {
                requireContext().getString(R.string.gifs_label)
            } else {
                requireContext().getString(R.string.pictures_label)
            }
            tab.text = tabTitle
        }.attach()
    }
}