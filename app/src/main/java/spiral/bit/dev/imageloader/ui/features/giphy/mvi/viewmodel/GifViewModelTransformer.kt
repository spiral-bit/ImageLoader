package spiral.bit.dev.imageloader.ui.features.giphy.mvi.viewmodel

import spiral.bit.dev.imageloader.ui.features.giphy.mvi.GiphyFeature

class GifViewModelTransformer : (GiphyFeature.State) -> GiphyViewModel() {
    override fun invoke(uiState: GiphyFeature.State): GiphyViewModel {
        return GiphyViewModel(gifDTO = uiState.gif)
    }
}