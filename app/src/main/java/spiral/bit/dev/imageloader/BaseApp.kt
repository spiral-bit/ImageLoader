package spiral.bit.dev.imageloader

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import spiral.bit.dev.imageloader.di.networkModule

class BaseApp : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(networkModule)
    }

    override fun onCreate() {
        super.onCreate()
    }
}