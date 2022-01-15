package spiral.bit.dev.imageloader.ui.common.factories

import android.view.View

class DetailsListBuilder {

    private val factories = HashMap<String?, () -> List<View>>()
    // gif, list(imageview, textview, cardview)
    // picture, list (imageview, textview, textview)

    fun retrieveBuilder(className: String) = factories[className]

    fun setBuilderFor(className: String?, function: () -> List<View>) {
        factories[className] = function
    }
}