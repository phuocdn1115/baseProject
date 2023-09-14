package luvnhi.luvbap.utils

import android.content.Context
import android.content.Intent
import luvnhi.luvbap.ui.MainActivity

object NavigationUtils {
    fun navigateToMainActivity(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}
