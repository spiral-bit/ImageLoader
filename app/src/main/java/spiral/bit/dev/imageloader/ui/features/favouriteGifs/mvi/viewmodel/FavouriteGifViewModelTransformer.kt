package spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.viewmodel

import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFeature

class FavouriteGifViewModelTransformer : (FavouriteGifsFeature.State) -> FavouriteGifViewModel {
    override fun invoke(uiState: FavouriteGifsFeature.State): FavouriteGifViewModel {
        return FavouriteGifViewModel(
            gifs = uiState.gifs,
            error = uiState.error,
            isLoading = uiState.isLoading,
            isOpenDetailsScreen = uiState.isOpenDetailsScreen
        )
    }
}