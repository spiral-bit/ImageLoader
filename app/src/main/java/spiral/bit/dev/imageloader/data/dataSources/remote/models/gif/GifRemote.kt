package spiral.bit.dev.imageloader.data.dataSources.remote.models.gif

import com.google.gson.annotations.SerializedName

data class GifRemote(
    @SerializedName("data")
    val dataRemote: Data,
    val meta: Meta
)