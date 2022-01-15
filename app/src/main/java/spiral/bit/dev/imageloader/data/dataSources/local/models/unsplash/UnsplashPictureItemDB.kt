package spiral.bit.dev.imageloader.data.dataSources.local.models.unsplash

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import spiral.bit.dev.imageloader.ui.common.adapters.ListItem

@Entity(tableName = "pictures")
@Parcelize
data class UnsplashPictureItemDB(
    @PrimaryKey val id: String,
    val description: String?,
    @Embedded val urls: UnsplashPhotoUrls,
    @Embedded val user: UnsplashUser
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