package luvnhi.luvbap.utils

import android.content.Context
import android.graphics.Color
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.blankj.utilcode.util.Utils
import luvnhi.luvbap.R
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object CommonUtils {

    /**
     * Get app component
     */
    fun getAppComponent(): Context = Utils.getApp().applicationContext

    fun randomColor() =
        Color.argb(180, Random().nextInt(256), Random().nextInt(256), Random().nextInt(256))

    val colorTransparent = getAppComponent().getColor(R.color.color_00D9D9D9)
    val colorBlack = getAppComponent().getColor(R.color.color_090F1F)

    fun getScreen(context: Context): DisplayMetrics {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(dm)
        return dm
    }

    fun String.getColor() : Int = try {
        Color.parseColor(this)
    } catch (t: Throwable) {
        randomColor()
    }

    fun milliSecondsToSeconds(milliSeconds: Long): Int =
        TimeUnit.MILLISECONDS.toSeconds(milliSeconds).toInt()

    fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    fun milliSecondsToDate(milliSeconds: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}

