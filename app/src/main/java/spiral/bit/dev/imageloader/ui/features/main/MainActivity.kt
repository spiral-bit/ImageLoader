package spiral.bit.dev.imageloader.ui.features.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance
import spiral.bit.dev.imageloader.R

class MainActivity : AppCompatActivity(), DIAware {

    override val di: DI by closestDI()
    private val baseUrl by instance<String>(tag = "base_url")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}