package spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.event

import spiral.bit.dev.imageloader.ui.features.favouriteGifs.mvi.FavouriteGifsFeature

class FavouriteGifUiEventTransformer : (FavouriteGifUiEvent) -> FavouriteGifsFeature.Wish {
    override fun invoke(uiEvent: FavouriteGifUiEvent) = when (uiEvent) {
        is FavouriteGifUiEvent.OpenGifItem -> FavouriteGifsFeature.Wish.OpenFavouriteGif(uiEvent.gifUI)
        is FavouriteGifUiEvent.DeleteGifItem -> FavouriteGifsFeature.Wish.DeleteFavouriteGif(uiEvent.gifUI)
        is FavouriteGifUiEvent.LoadGifItems -> FavouriteGifsFeature.Wish.FetchFavouriteGifs
    }
}