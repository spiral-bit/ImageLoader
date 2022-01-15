package spiral.bit.dev.imageloader.data.dataSources.remote.models.gif

import com.google.gson.annotations.SerializedName

data class FixedHeight(
    @SerializedName("height")
    val fixedHeightHeight: String,
    @SerializedName("mp4")
    val fixedHeightMp4: String,
    @SerializedName("mp4_size")
    val fixedHeightMp4Size: String,
    @SerializedName("size")
    val fixedHeightSize: String,
    @SerializedName("url")
    val fixedHeightUrl: String,
    @SerializedName("webp")
    val fixedHeightWebp: String,
    @SerializedName("webp_size")
    val fixedHeightWebpSize: String,
    @SerializedName("width")
    val fixedHeightWidth: String
)