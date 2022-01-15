package spiral.bit.dev.imageloader.data.dataSources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import spiral.bit.dev.imageloader.data.dataSources.local.dao.GiphyDao
import spiral.bit.dev.imageloader.data.dataSources.local.dao.UnsplashDao
import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.GifDB
import spiral.bit.dev.imageloader.data.dataSources.local.models.unsplash.UnsplashPictureItemDB

@Database(entities = [GifDB::class, UnsplashPictureItemDB::class], version = 8, exportSchema = false)
abstract class FavouritesDatabase : RoomDatabase() {
    abstract fun giphyDao(): GiphyDao
    abstract fun unsplashDao(): UnsplashDao
}