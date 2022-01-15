package spiral.bit.dev.imageloader.ui.features.favouritePictures.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.provider
import spiral.bit.dev.imageloader.databinding.FragmentFavouritePicturesBinding
import spiral.bit.dev.imageloader.ui.base.BaseFragment
import spiral.bit.dev.imageloader.ui.base.binding
import spiral.bit.dev.imageloader.ui.common.adapters.diffCallback
import spiral.bit.dev.imageloader.ui.common.paging.emptyPagingData
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.favouritePictureDelegate
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesBinding
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesFeature
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.event.FavouritePicturesUiEvent
import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.viewmodel.FavouritePicturesViewModel

class FavouritePicturesFragment : BaseFragment<FragmentFavouritePicturesBinding, FavouritePicturesUiEvent, FavouritePicturesViewModel>(
    FragmentFavouritePicturesBinding::inflate
), DIAware {

    override val di: DI by closestDI()
    private val favouritePicturesFeature by provider<FavouritePicturesFeature>(tag = "favouritePicturesFeature")
    private val favouritePicturesBinding by lazy { FavouritePicturesBinding(this, favouritePicturesFeature.invoke()) }
    private val favouritePicturesAdapter = AsyncListDifferDelegationAdapter(diffCallback, favouritePictureDelegate(openDetails = {
        onNext(FavouritePicturesUiEvent.OpenPictures(it))
    }, deletePicture = {
        onNext(FavouritePicturesUiEvent.DeletePictures(it))
    }))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProperties()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() = binding {
        favouritePicturesRecyclerView.adapter = favouritePicturesAdapter
    }

    private fun initProperties() {
        favouritePicturesBinding.setup(this)
    }

    override fun accept(favouritePicturesViewModel: FavouritePicturesViewModel?) {
        lifecycleScope.launch {
            favouritePicturesAdapter.items = favouritePicturesViewModel?.pictures ?: emptyList()
        }
    }
}