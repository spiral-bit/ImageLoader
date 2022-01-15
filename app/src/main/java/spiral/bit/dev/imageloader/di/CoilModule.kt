package spiral.bit.dev.imageloader.di

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import org.kodein.di.DI
import org.kodein.di.bindSingleton

fun coilModule(fragmentContext: Context) = DI.Module(name = "coilModule") {
    bindSingleton {
        ImageLoader.Builder(fragmentContext)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder(fragmentContext))
                } else {
                    add(GifDecoder())
                }
            }.build()
    }
}