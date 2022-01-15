package spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.viewmodel

import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesFeature

class FavouritePicturesViewModelTransformer : (FavouritePicturesFeature.State) -> FavouritePicturesViewModel {
    override fun invoke(uiState: FavouritePicturesFeature.State): FavouritePicturesViewModel {
        return FavouritePicturesViewModel(pictures = uiState.pictures, error = uiState.error, isLoading = uiState.isLoading)
    }
}