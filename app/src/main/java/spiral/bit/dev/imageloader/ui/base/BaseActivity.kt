package spiral.bit.dev.imageloader.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<BINDING : ViewBinding>(
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> BINDING
) : AppCompatActivity() {

    var binding: BINDING? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = findViewById<ViewGroup>(android.R.id.content)
        inflateBinding(layoutInflater, rootView, false).also { binding = it }
        setContentView(checkNotNull(binding).root)
    }
}

fun <BINDING : ViewBinding> BaseActivity<BINDING>.binding(block: BINDING.() -> Unit) {
    binding?.apply(block)
}