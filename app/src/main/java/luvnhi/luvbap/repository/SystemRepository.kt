package luvnhi.luvbap.repository

import luvnhi.luvbap.local.preferences.PreferencesKey.IS_ENABLE_DARK_MODE
import luvnhi.luvbap.local.preferences.PreferencesKey.IS_FIRST_TIME_OPEN_APP
import luvnhi.luvbap.local.preferences.PreferencesManager
import luvnhi.luvbap.utils.FileUtils
import javax.inject.Inject

class SystemRepository @Inject constructor(
    private val preferencesManager: PreferencesManager,
) {
    fun checkIsEnableDarkMode(): Boolean? =
        preferencesManager.getData(IS_ENABLE_DARK_MODE, Boolean::class)

    fun setEnableDarkMode(value: Boolean) {
        preferencesManager.save(IS_ENABLE_DARK_MODE, value)
    }

    fun checkIsFirstTimeOpenApp() = preferencesManager.getData(IS_FIRST_TIME_OPEN_APP, Boolean::class)

    fun saveFirstTimeOpenApp() = preferencesManager.save(IS_FIRST_TIME_OPEN_APP, true)

    fun getDataLocal() = FileUtils.localStorageQuery()
}
