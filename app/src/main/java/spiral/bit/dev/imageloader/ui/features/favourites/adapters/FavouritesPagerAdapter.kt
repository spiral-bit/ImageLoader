package spiral.bit.dev.imageloader.ui.features.favourites.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.view.FavouriteGifsFragment
import spiral.bit.dev.imageloader.ui.features.favouritePictures.view.FavouritePicturesFragment

class FavouritesPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavouriteGifsFragment()
            1 -> FavouritePicturesFragment()
            else -> FavouriteGifsFragment()
        }
    }

    override fun getItemCount() = 2
}