package luvnhi.luvbap.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.x5.pbn.utils.StatusBarUtils

@RequiresApi(Build.VERSION_CODES.P)
abstract class BaseActivity<BINDING : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: BINDING
    val  activityLauncher = BetterActivityResult.registerActivityForResult(this)

@SuppressLint("SourceLockedOrientationActivity")
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //displayMetrics = CommonUtils.getScreen(this)
        binding = DataBindingUtil.setContentView(this, getContentLayout())
        initView()
        initListener()
        observerLiveData()

    }

    abstract fun getContentLayout(): Int

    abstract fun initView()

    abstract fun initListener()

    abstract fun observerLiveData()

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /**@param event detect clear focus edittext when touch outside */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view: View? = currentFocus
            if (view is EditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    //hideKeyboard(this)
                    view.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    protected fun paddingStatusBar(view: View) {
        view.setPadding(0, StatusBarUtils.getStatusBarHeight(baseContext), 0, 0)
    }

}