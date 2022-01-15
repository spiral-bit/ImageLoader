package spiral.bit.dev.imageloader.data.dataSources.local.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import spiral.bit.dev.imageloader.data.dataSources.local.models.giphy.GifDB

@Dao
interface GiphyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gif: GifDB): Completable
    
    @Query("DELETE FROM gifs WHERE id == :gifUiId")
    fun deleteGifById(gifUiId: String): Completable

    @Query("SELECT * FROM gifs")
    fun observe(): Observable<List<GifDB>>
}