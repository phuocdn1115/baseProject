package luvnhi.luvbap

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import luvnhi.luvbap.repository.SystemRepository
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application(){

    @Inject
    lateinit var systemRepository: SystemRepository

    companion object {
        lateinit var instance: MyApp
        fun getContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupTheme()
    }

    private fun setupTheme() {
        if (systemRepository.checkIsEnableDarkMode() == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}