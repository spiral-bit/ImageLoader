package spiral.bit.dev.imageloader.ui.features.main.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import spiral.bit.dev.imageloader.R
import spiral.bit.dev.imageloader.databinding.ActivityMainBinding
import spiral.bit.dev.imageloader.ui.base.BaseActivity
import spiral.bit.dev.imageloader.ui.base.binding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavigation()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        setUpBottomSheet()
    }

    private fun setUpBottomSheet() = binding {
        bottomNavigationView.apply {
            itemIconTintList = null
        }
    }

    private fun setUpNavigation() = binding {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}