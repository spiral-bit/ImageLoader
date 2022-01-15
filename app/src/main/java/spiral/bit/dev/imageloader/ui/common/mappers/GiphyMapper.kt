package spiral.bit.dev.imageloader.ui.common.mappers

import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.DataDB
import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.GifDB
import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.ImagesDB
import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.MetaDB
import spiral.bit.dev.imageloader.data.dataSources.remote.models.gif.GifRemote
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.DataUI
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.GifUI
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.ImagesUI
import spiral.bit.dev.imageloader.ui.features.favouriteGifs.models.MetaUI

class GiphyMapper {

    fun toEntityGif(gifRemote: GifRemote): GifDB {
        gifRemote.dataRemote.apply {
            return GifDB(
                primaryKeyId = gifRemote.dataRemote.id,
                DataDB(
                    type, id, slug, url, bitly_url, bitly_gif_url, embed_url, username, source, rating, content_url, user,
                    source_tld, source_post_url, is_sticker, import_datetime, trending_datetime, ImagesDB(images.fixedHeight), title
                ),
                MetaDB(
                    gifRemote.meta.msg,
                    gifRemote.meta.response_id,
                    gifRemote.meta.status
                )
            )
        }
    }

    fun toEntityGif(gifUI: GifUI): GifDB {
        gifUI.dataUI.apply {
            return GifDB(
                primaryKeyId = gifUI.dataUI.id,
                dataDB = DataDB(
                    type, id, slug, url, cutUrl, bitlyGifUrl, embedUrl, username, source, rating, contentUrl, user, sourceTld,
                    sourcePostUrl, isSticker, importDatetime, trendingDatetime, ImagesDB(images.fixedHeight), title
                ),
                metaDB = MetaDB(
                    gifUI.metaUI.msg,
                    gifUI.metaUI.responseId,
                    gifUI.metaUI.status
                )
            )
        }
    }


    fun toUIGif(gifDB: GifDB): GifUI {
        gifDB.dataDB.apply {
            return GifUI(
                primaryKeyId = gifDB.dataDB.id,
                DataUI(
                    type,
                    id,
                    slug,
                    url,
                    cutUrl,
                    bitlyGifUrl,
                    embedUrl,
                    username,
                    source,
                    rating,
                    contentUrl,
                    user,
                    sourceTld,
                    sourcePostUrl,
                    isSticker,
                    importDatetime,
                    trendingDatetime,
                    ImagesUI(images.fixedHeight),
                    title
                ),
                MetaUI(
                    gifDB.metaDB.msg,
                    gifDB.metaDB.responseId,
                    gifDB.metaDB.status
                )
            )
        }
    }
}