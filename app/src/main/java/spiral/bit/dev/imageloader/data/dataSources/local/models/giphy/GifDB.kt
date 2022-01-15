package spiral.bit.dev.imageloader.data.dataSources.local.models.giphy

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gifs")
data class GifDB(
    @PrimaryKey val primaryKeyId: String,
    @Embedded val dataDB: DataDB,
    @Embedded val metaDB: MetaDB
)
