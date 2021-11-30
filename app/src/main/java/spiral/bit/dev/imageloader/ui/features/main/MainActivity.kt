package spiral.bit.dev.imageloader.ui.features.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.instance
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.ActivityMainBinding
import spiral.bit.dev.imageloader.ui.base.BaseActivity
import spiral.bit.dev.imageloader.ui.base.binding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate), DIAware {

    override val di: DI by closestDI()
    private val baseUrl by instance<String>(tag = "base_url")

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        setUpViews()
    }

    private fun setUpViews() {
        setUpBottomSheet()
    }

    private fun setUpBottomSheet() = binding {
        bottomNavigationView.apply {
            itemIconTintList = null
        }
    }
}