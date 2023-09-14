package luvnhi.luvbap.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import kotlin.reflect.KClass

class PreferencesManager (context: Context) {

    private var mPrefs: SharedPreferences

    companion object {
        const val name = "PREFERENCES_STORAGE"
    }

    init {
        mPrefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    fun <T: Any> save(key: String, t: T?) {
        try {
            if (t == null) {
                putString(key, null)
            } else {
                val str = Gson().toJson(t)
                putString(key, str)
            }
        }catch (e: Exception) { }
    }

    fun <T : Any> getData(key: String, clazz: KClass<T>): T? {
        val string = getString(key) ?: return null
        try {
            return Gson().fromJson(string, clazz.java)
        } catch (e: Exception) {}
        return null
    }

    private fun putString(key: String, value: String?) {
        with(mPrefs.edit()) {
            putString(key, value)
            apply()
        }
    }

    private fun getString(key: String?): String? {
        return mPrefs.getString(key, null)
    }

    fun remove(key: String?) {
        mPrefs.edit().remove(key).apply()
    }
}