package luvnhi.luvbap.utils

import android.app.Activity
import com.x5.pbn.utils.StatusBarUtils
import luvnhi.luvbap.MyApp

fun Activity.setStatusBarDarkOrLight() {
    if (MyApp.instance.systemRepository.checkIsEnableDarkMode() == true) {
        StatusBarUtils.makeStatusBarTransparentAndLight(this)
    } else {
        StatusBarUtils.makeStatusBarTransparentAndDark(this)
    }
}