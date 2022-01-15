package spiral.bit.dev.imageloader.data.dataSources.remote.models.gif

import com.google.gson.annotations.SerializedName

data class Images(
    @Transient @SerializedName("480w_still") val wStill: WStill,
    @Transient @SerializedName("downsized") val downsized: Downsized,
    @Transient @SerializedName("downsized_large") val downsizedLarge: DownsizedLarge,
    @Transient @SerializedName("downsized_medium") val downsizedMedium: DownsizedMedium,
    @Transient @SerializedName("downsized_small") val downsizedSmall: DownsizedSmall,
    @Transient @SerializedName("downsized_still") val downsizedStill: DownsizedStill,
    @SerializedName("fixed_height") val fixedHeight: FixedHeight,
    @Transient @SerializedName("fixed_height_downsampled") val fixedHeightDownsampled: FixedHeightDownsampled,
    @Transient @SerializedName("fixed_height_small") val fixedHeightSmall: FixedHeightSmall,
    @Transient @SerializedName("fixed_height_small_still") val fixedHeightSmallStill: FixedHeightSmallStill,
    @Transient @SerializedName("fixed_height_still") val fixedHeightStill: FixedHeightStill,
    @Transient @SerializedName("fixed_width") val fixedWidth: FixedWidth,
    @Transient @SerializedName("fixed_width_downsampled") val fixedWidthDownsampled: FixedWidthDownsampled,
    @Transient @SerializedName("fixed_width_small") val fixedWidthSmall: FixedWidthSmall,
    @Transient @SerializedName("fixed_width_small_still") val fixedWidthSmallStill: FixedWidthSmallStill,
    @Transient @SerializedName("fixed_width_still") val fixedWidthStill: FixedWidthStill,
    @Transient @SerializedName("hd") val hd: Hd,
    @Transient @SerializedName("looping") val looping: Looping,
    @Transient @SerializedName("original") val original: Original,
    @Transient @SerializedName("original_mp4") val originalMp4: OriginalMp4,
    @Transient @SerializedName("original_still") val originalStill: OriginalStill,
    @Transient @SerializedName("preview") val preview: Preview,
    @Transient @SerializedName("preview_gif") val previewGif: PreviewGif,
    @Transient @SerializedName("preview_webp") val previewWebp: PreviewWebp
)