package spiral.bit.dev.imageloader.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<BINDING : ViewBinding>(private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> BINDING) : Fragment() {

    var binding: BINDING? = null
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflateBinding(inflater, container, false).also { binding = it }.root
    }
}

fun <BINDING : ViewBinding> BaseFragment<BINDING>.binding(block: BINDING.() -> Unit) {
    binding?.apply(block)
}