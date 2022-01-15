package spiral.bit.dev.imageloader.ui.features.feed.mvi.viewmodel

import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedFeature

class FeedViewModelTransformer : (FeedFeature.State) -> FeedViewModel {
    override fun invoke(uiState: FeedFeature.State): FeedViewModel {
        return FeedViewModel(
            pictures = uiState.pictures,
            isLoading = uiState.isLoading,
            error = uiState.error
        )
    }
}