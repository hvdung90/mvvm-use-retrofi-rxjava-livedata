package com.lixi.libcore.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import java.io.Serializable

class AppPreferenceUtils(appContext: Context) {

    private val sharedPref: SharedPreferences = appContext.getSharedPreferences(
            appContext.applicationContext.packageName,
            Context.MODE_PRIVATE
    )

    fun set(key: String, data: Any?) {
        if (key.isBlank()) throw Exception("Preference key is blank!")
        val editor = this@AppPreferenceUtils.sharedPref.edit()

        if (data == null) {
            editor.remove(key)
            editor.apply()
            return
        }

        when (data) {
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is String -> editor.putString(key, data)
            is Serializable -> {
                editor.putString(key, getDefaultGsonParser().toJson(data))
            }
        }

        editor.apply()
    }

    fun <T> get(key: String, dataType: Class<T>, defaultValue: T? = null): T? {
        if (key.isBlank()) throw Exception("Preference key is blank!")

        if (!this@AppPreferenceUtils.sharedPref.contains(key)) {
            return null
        }

        return when (dataType) {
            Int::class.java -> this@AppPreferenceUtils.sharedPref.getInt(key, (defaultValue as? Int)
                    ?: -1) as? T
            Long::class.java -> this@AppPreferenceUtils.sharedPref.getLong(key, (defaultValue as? Long)
                    ?: -1) as? T
            Boolean::class.java -> this@AppPreferenceUtils.sharedPref.getBoolean(key, (defaultValue as? Boolean)
                    ?: false) as? T
            Float::class.java -> this@AppPreferenceUtils.sharedPref.getFloat(key, (defaultValue as? Float)
                    ?: 0.0f) as? T
            Double::class.java -> this@AppPreferenceUtils.sharedPref.getFloat(key, (defaultValue as? Float)
                    ?: 0.0f) as? T
            String::class.java -> this@AppPreferenceUtils.sharedPref.getString(key, (defaultValue as? String)
                    ?: "") as? T
            else -> {
                getDefaultGsonParser().fromJson<T>(this@AppPreferenceUtils.sharedPref.getString(key, "{}"), dataType)
            }
        }
    }

    fun clear(key: String?) {
        if (TextUtils.isEmpty(key)) {
            sharedPref.edit().clear()
            return
        }

        if (this@AppPreferenceUtils.sharedPref.contains(key)) {
            sharedPref.edit().remove(key).commit()
        }
    }

    companion object {
        private var preference: AppPreferenceUtils? = null
        internal fun init(appContext: Context) {
            preference = AppPreferenceUtils(appContext)
        }

        fun default(): AppPreferenceUtils {
            return preference ?: throw Exception("")
        }
    }

    fun getDefaultGsonParser(): Gson {
        val gsonBuilder = GsonBuilder()
                .setLenient()
                .serializeNulls()

        gsonBuilder.setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return false
            }

            override fun shouldSkipField(field: FieldAttributes?): Boolean {
                val unwrappedField = field ?: return false
                return (unwrappedField.getAnnotation(Annotations.JsonableExcludeField::class.java) != null)
                        || (unwrappedField.getAnnotation(Expose::class.java) != null)
            }

        })

        return gsonBuilder.create()
    }

    object Annotations {
        @Target(AnnotationTarget.FIELD)
        annotation class JsonableExcludeField
    }
}