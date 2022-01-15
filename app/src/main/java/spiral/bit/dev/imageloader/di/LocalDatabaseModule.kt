package spiral.bit.dev.imageloader.di

import android.content.Context
import androidx.room.Room
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import spiral.bit.dev.imageloader.data.dataSources.local.FavouritesDatabase

fun localDatabaseModule(appContext: Context) = DI.Module(name = "localDatabaseModule") {
    bindDatabase(appContext)
    bindDao()
}

fun DI.Builder.bindDatabase(appContext: Context) {
    bindSingleton(tag = "appDb") {
        Room.databaseBuilder(
            appContext,
            FavouritesDatabase::class.java,
            "favourites.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}

fun DI.Builder.bindDao() {
    bindSingleton(tag = "giphyDao") {
        val favouritesDatabase = instance<FavouritesDatabase>(tag = "appDb")
        favouritesDatabase.giphyDao()
    }
    bindSingleton(tag = "unsplashDao") {
        val favouritesDatabase = instance<FavouritesDatabase>(tag = "appDb")
        favouritesDatabase.unsplashDao()
    }
}