package com.tiny.covidtracker.ui.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.SystemClock
import android.text.Html
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.math.BigDecimal
import java.util.*
import java.util.regex.Pattern

fun EditText.safeHint(): String {
    if (parent is TextInputLayout && TextUtils.isEmpty((parent as TextInputLayout).hint))
        return (parent as TextInputLayout).hint.toString()
    return hint?.toString() ?: ""
}

fun EditText.str(): String {
    return text.toString()
}

fun EditText.getAmountServer(): String {
    return text.toString().replace("[^\\d-.]".toRegex(), "")
}

fun String.getAmountServer(): String {
    return this.replace("[^\\d-.]".toRegex(), "")
}

fun String.getAmountBig(): BigDecimal {
    return BigDecimal(this.getAmountServer())
}

fun String.getAmountBig1(): BigDecimal {
    return BigDecimal(this)
}

fun String.mask() {

}

fun String.html(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(this)
    }
}

/**
 * Extension method to provide simpler access to {@link View#getResources()#getString(int)}.
 */
fun View.getString(stringResId: Int): String = resources.getString(stringResId)

/**
 * Extension method to show a keyboard for View.
 */
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun JSONObject.getSafeString(key: String): String {
    if (this.has(key))
        return getString(key)
    return ""
}

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        try {
            onSafeClick(it)
        } catch (e: java.lang.Exception) {
            Log.wtf("EX", e)
        }
    }
    setOnClickListener(safeClickListener)
}

fun Date.getLastYear(): Date {
    return Calendar.getInstance().let {
        it.time = this
        it.set(Calendar.YEAR, it.get(Calendar.YEAR) - 1)
        it.time
    }
}

fun Date.getLastWeek(): Date {
    return Calendar.getInstance().let {
        it.time = this
        it.add(Calendar.DAY_OF_YEAR, -7)
        it.time
    }
}


class SafeClickListener(
    private var defaultInterval: Int = 1000,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}


fun View.getBitmapCache(defaultColor: Int = 0x000066ad): Bitmap? {
    try {
        val bitmap =
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(defaultColor)
        draw(canvas)

        return bitmap
    } catch (e: Exception) {
    }
    return null
}

fun View.setPaddingHorizontal(padding: Int) {
    setPadding(padding, paddingTop, padding, paddingBottom)
}

fun View.setPaddingVertical(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, padding)
}

val EMAIL_ADDRESS_PATTERN = Pattern
    .compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
    )

fun String.isValidEmail(): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}

fun String?.valueNotEmpty(): String? {
    if (this.isNullOrEmpty())
        return null
    return this
}

