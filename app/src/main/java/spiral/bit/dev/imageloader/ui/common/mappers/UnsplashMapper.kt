package spiral.bit.dev.imageloader.ui.common.mappers

import spiral.bit.dev.imageloader.data.dataSources.local.models.unsplash.UnsplashPictureItemDB
import spiral.bit.dev.imageloader.data.dataSources.remote.models.unsplash.UnsplashPictureItem
import spiral.bit.dev.imageloader.ui.features.favouritePictures.models.UnsplashPictureItemUI

class UnsplashMapper {

    fun toEntityPicture(remoteUnsplashPictureItem: UnsplashPictureItem): UnsplashPictureItemDB {
        remoteUnsplashPictureItem.apply {
            return UnsplashPictureItemDB(
                id,
                description,
                UnsplashPictureItemDB.UnsplashPhotoUrls(
                    urls.raw,
                    urls.full,
                    urls.regular,
                    urls.small,
                    urls.thumb,
                ),
                UnsplashPictureItemDB.UnsplashUser(user.name, user.username)
            )
        }
    }

    fun toEntityPicture(unsplashPictureItemUI: UnsplashPictureItemUI): UnsplashPictureItemDB {
        unsplashPictureItemUI.apply {
            return UnsplashPictureItemDB(
                id,
                description,
                UnsplashPictureItemDB.UnsplashPhotoUrls(
                    urls.raw,
                    urls.full,
                    urls.regular,
                    urls.small,
                    urls.thumb,
                ),
                UnsplashPictureItemDB.UnsplashUser(user.name, user.username)
            )
        }
    }

    fun toUIPicture(unsplashPictureItemDB: UnsplashPictureItemDB): UnsplashPictureItemUI {
        unsplashPictureItemDB.apply {
            return UnsplashPictureItemUI(
                id,
                description,
                UnsplashPictureItemUI.UnsplashPhotoUrls(
                    urls.raw,
                    urls.full,
                    urls.regular,
                    urls.small,
                    urls.thumb,
                ),
                UnsplashPictureItemUI.UnsplashUser(user.name, user.username)
            )
        }
    }

    fun toUIPicture(remoteUnsplashPicture: UnsplashPictureItem): UnsplashPictureItemUI {
        remoteUnsplashPicture.apply {
            return UnsplashPictureItemUI(
                id,
                description,
                UnsplashPictureItemUI.UnsplashPhotoUrls(
                    urls.raw,
                    urls.full,
                    urls.regular,
                    urls.small,
                    urls.thumb,
                ),
                UnsplashPictureItemUI.UnsplashUser(user.name, user.username)
            )
        }
    }
}