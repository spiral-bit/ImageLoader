package spiral.bit.dev.imageloader.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

abstract class BaseFragment<VIEW_BINDING : ViewBinding, UI_EVENT, VIEW_MODEL>(
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> VIEW_BINDING
) : Fragment(), ObservableSource<UI_EVENT>, Consumer<VIEW_MODEL> {

    private val source = PublishSubject.create<UI_EVENT>()

    var binding: VIEW_BINDING? = null
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflateBinding(inflater, container, false).also { binding = it }.root
    }

    protected fun onNext(uiEvent: UI_EVENT) {
        source.onNext(uiEvent)
    }

    override fun subscribe(observer: Observer<in UI_EVENT>) {
        source.subscribe(observer)
    }
}

fun <VIEW_BINDING : ViewBinding, UI_EVENT, VIEW_MODEL> BaseFragment<VIEW_BINDING, UI_EVENT, VIEW_MODEL>.binding(
    block: VIEW_BINDING.() -> Unit
) {
    binding?.apply(block)
}