package spiral.bit.dev.imageloader.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BindingFragment<VIEW_BINDING : ViewBinding>(
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> VIEW_BINDING
) : Fragment() {

    var binding: VIEW_BINDING? = null
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflateBinding(inflater, container, false).also { binding = it }.root
    }
}

fun <VIEW_BINDING : ViewBinding> BindingFragment<VIEW_BINDING>.binding(
    block: VIEW_BINDING.() -> Unit
) {
    binding?.apply(block)
}