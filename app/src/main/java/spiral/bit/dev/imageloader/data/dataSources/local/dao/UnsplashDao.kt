package spiral.bit.dev.imageloader.data.dataSources.local.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import spiral.bit.dev.imageloader.data.dataSources.local.models.unsplash.UnsplashPictureItemDB

@Dao
interface UnsplashDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(unsplashPictureItemDB: UnsplashPictureItemDB): Completable

    @Delete
    fun delete(unsplashPictureItemDB: UnsplashPictureItemDB): Completable

    @Query("DELETE FROM pictures WHERE id == :unsplashPictureItemId")
    fun deletePictureById(unsplashPictureItemId: String): Completable

    @Query("SELECT * FROM pictures")
    fun observe(): Observable<List<UnsplashPictureItemDB>>
}

enum class OrderBy(value: String) { OLDEST("oldest"), LATEST("latest"), POPULAR("popular") }
