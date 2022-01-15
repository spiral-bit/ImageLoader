package spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.event

import spiral.bit.dev.imageloader.ui.features.favouritePictures.mvi.FavouritePicturesFeature

class FavouritePicturesUiEventTransformer : (FavouritePicturesUiEvent) -> FavouritePicturesFeature.Wish {
    override fun invoke(uiEvent: FavouritePicturesUiEvent) = when (uiEvent) {
        is FavouritePicturesUiEvent.OpenPictures -> FavouritePicturesFeature.Wish.OpenPicture(uiEvent.unsplashPictureUI)
        is FavouritePicturesUiEvent.DeletePictures -> FavouritePicturesFeature.Wish.DeletePicture(uiEvent.unsplashPictureUI)
        is FavouritePicturesUiEvent.FetchPictures -> FavouritePicturesFeature.Wish.FetchFavouritePictures
    }
}