package spiral.bit.dev.imageloader.ui.features.feed.mvi.event

import spiral.bit.dev.imageloader.ui.features.feed.mvi.FeedFeature

class FeedUiEventTransformer : (FeedUiEvent) -> FeedFeature.Wish {
    override fun invoke(uiEvent: FeedUiEvent): FeedFeature.Wish = when (uiEvent) {
        is FeedUiEvent.GetPictures -> FeedFeature.Wish.FetchPictures
        is FeedUiEvent.SavePicture -> FeedFeature.Wish.SavePicture(uiEvent.unsplashPictureUI)
    }
}