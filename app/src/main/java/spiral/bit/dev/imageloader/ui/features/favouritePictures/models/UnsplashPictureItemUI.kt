package spiral.bit.dev.imageloader.ui.features.favouritePictures.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import spiral.bit.dev.imageloader.ui.common.adapters.ListItem

@Parcelize
data class UnsplashPictureItemUI(
    val id: String,
    val description: String?,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable, ListItem {

    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    ) : Parcelable

    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ) : Parcelable {
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageSearchApp&utm_medium=referral"
    }
}