package luvnhi.luvbap.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.view.View
import com.x5.pbn.utils.StatusBarUtils
import dagger.hilt.android.AndroidEntryPoint
import luvnhi.luvbap.MyApp
import luvnhi.luvbap.R
import luvnhi.luvbap.base.BaseActivity
import luvnhi.luvbap.databinding.ActivitySplashBinding
import luvnhi.luvbap.utils.NavigationUtils
import luvnhi.luvbap.utils.setStatusBarDarkOrLight

@SuppressLint("NewApi", "CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        setStatusBarDarkOrLight()
        initVideoSplash()
    }

    private fun initVideoSplash() {
        val rawRes =
            if (MyApp.instance.systemRepository.checkIsEnableDarkMode() == true) R.raw.splash_dark_mode else R.raw.splash_light_mode
        binding.animation.setAnimation(rawRes)
        binding.animation.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                NavigationUtils.navigateToMainActivity(this@SplashActivity)
                finish()
            }
        })
        binding.animation.playAnimation()
    }
    override fun initListener() {
    }

    override fun observerLiveData() {
    }
}