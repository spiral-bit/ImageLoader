package spiral.bit.dev.imageloader.ui.common.extensions

import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG
) = Snackbar.make(this, messageRes, length).show()

inline fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    action: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, action)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, action: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.action()
    snack.show()
}

fun View.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg, duration).show()
}

fun View.toast(@StringRes msg: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg, duration).show()
}