package spiral.bit.dev.imageloader.ui.features.favouriteGifs.view

import android.os.Bundle
import android.view.View
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.provider
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.FragmentFavouriteGifsBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding
import spiral.bit.dev.imageloader.ui.common.adapters.diffCallback
import spiral.bit.dev.imageloader.ui.common.extensions.toast
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.favouriteGifDelegate
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFeature
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFragmentBindings
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.event.FavouriteGifUiEvent
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.viewmodel.FavouriteGifViewModel

class FavouriteGifsFragment : BaseFragment<FragmentFavouriteGifsBinding, FavouriteGifUiEvent, FavouriteGifViewModel>(
    FragmentFavouriteGifsBinding::inflate
), DIAware {

    override val di: DI by closestDI()
    private val favouriteGifsFeature by provider<FavouriteGifsFeature>(tag = "favouriteGifsFeature")
    private val favouriteGifsBinding by lazy { FavouriteGifsFragmentBindings(this, favouriteGifsFeature.invoke()) }
    private val favouritesAdapter = AsyncListDifferDelegationAdapter(
        diffCallback,
        favouriteGifDelegate(onItemOpen = { favouriteGifItem ->
            onNext(FavouriteGifUiEvent.OpenGifItem(favouriteGifItem))
        }, onItemDelete = { favouriteGIfItem ->
            onNext(FavouriteGifUiEvent.DeleteGifItem(favouriteGIfItem))
        })
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        initProperties()
    }

    private fun initProperties() {
        favouriteGifsBinding.setup(this)
    }

    private fun setUpRecyclerView() = binding {
        favouriteGifsRecyclerView.adapter = favouritesAdapter
    }

    override fun accept(favouriteGifViewModel: FavouriteGifViewModel) = binding {
        if (favouriteGifViewModel.isOpenDetailsScreen) {
            // todo findNavController().navigate() to details fragment
        }

        favouriteGifViewModel.error?.let { errorHappens ->
            root.toast(getString(R.string.error_format, errorHappens))
        } ?: run {
            favouritesAdapter.items = favouriteGifViewModel.gifs
        }
    }
}