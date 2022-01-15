package spiral.bit.dev.imageloader

import android.app.Application
import org.kodein.di.DI
import org.kodein.di.DIAware
import spiral.bit.dev.imageloader.di.*

class BaseApp : Application(), DIAware {
    override val di: DI by DI.lazy {
        importAll(
            commonModule,
            pagingModule,
            networkModule,
            repositoryModule,
            featuresModule,
            coilModule(this@BaseApp),
            localDatabaseModule(this@BaseApp)
        )
    }
}