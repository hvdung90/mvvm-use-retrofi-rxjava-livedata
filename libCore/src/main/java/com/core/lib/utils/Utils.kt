package com.core.lib.utils

import android.content.Context
import android.graphics.Color
import android.provider.Settings
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.WindowManager
import com.core.lib.common.retrofit.data.apiwrapper.ApiWrapperForListModel
import com.core.lib.common.retrofit.data.apiwrapper.ApiWrapperModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.HttpException
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class Utils {
//    var progressDialog: LoadingDialog? = null

    companion object {
        @JvmStatic
        private var INSTANCE: Utils? = null

        @JvmStatic
        fun getInstance(): Utils = INSTANCE
                ?: synchronized(this) {
                    INSTANCE ?: Utils().also { INSTANCE = it }
                }
    }


    fun handleApiReviewForListModelErrorForOne(t: Throwable, T: Class<*>): Any {
        try {
            if (t is HttpException) {
                var errorBody: String? = null
                try {
                    errorBody = t.response()?.errorBody()?.string()
                } catch (e: IOException) {
                    e.fillInStackTrace()
                }
                if (errorBody != null) {
                    return Gson().fromJson(errorBody, T)
                }
            } else {
                Log.d("error", t.message!!)
            }
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
        return Gson().fromJson("{}", T)
    }


    fun <T> handleApiReviewModelError(t: Throwable): ApiWrapperModel<T> {
        if (t is HttpException) {
            var errorBody: String? = null
            try {
                errorBody = t.response()?.errorBody()?.string()
            } catch (e: IOException) {
                e.fillInStackTrace()
            }
            if (errorBody != null) {
                return Gson().fromJson(errorBody, object : TypeToken<ApiWrapperModel<T>>() {}.type)
            }
        } else {
            if (t.message != null)
                Log.d("error", t.message!!)
        }
        return Gson().fromJson("{}", object : TypeToken<ApiWrapperModel<T>>() {}.type)
    }

    fun <T> handleApiReviewForListModelError(t: Throwable): ApiWrapperForListModel<T> {
        try {
            if (t is HttpException) {
                var errorBody: String? = null
                try {
                    errorBody = t.response()?.errorBody()?.string()
                } catch (e: IOException) {
                    e.fillInStackTrace()
                }
                if (errorBody != null) {
                    return Gson().fromJson(errorBody, object : TypeToken<ApiWrapperForListModel<ApiWrapperForListModel<T>>>() {}.type)
                }
            } else {
                Log.d("error", t.message!!)
            }
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
        return Gson().fromJson("{}", object : TypeToken<ApiWrapperForListModel<ApiWrapperForListModel<T>>>() {}.type)
    }

    fun getCurrencyVND(currency: Number?, isDiscount: Boolean): CharSequence? {
        return getCurrencyVNDBase(currency, true, isDiscount)
    }
    fun getCurrencyVNDNoSpace(currency: Number?, space: Boolean): CharSequence? {
        return getCurrencyVNDBase(currency, space, false)
    }

    fun getCurrencyVNDWithoutUnit(value: Double): String? {
        val nf: NumberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        nf.minimumFractionDigits = 0
        nf.isGroupingUsed = true
        return nf.format(value)
    }

    fun getCurrencyVNDBase(value: Number?, space: Boolean, isDiscount: Boolean): CharSequence? {
        val nf = NumberFormat.getNumberInstance(Locale.GERMAN)
        val dFormat = nf as DecimalFormat
        val formattedString: String = dFormat.format(value)
        val tp = TextPaint()
        tp.linkColor = Color.parseColor("#ffffff")
        val us = UnderlineSpan()
        us.updateDrawState(tp)
        val sp: SpannableString
        sp = if (space) {
            SpannableString("$formattedString Đ")
        } else {
            SpannableString(formattedString + "Đ")
        }
        //  sp.setSpan(us, sp.length - 1, sp.length, 0)
        if (isDiscount)
            return "-$sp"
        return sp
    }

    fun getScreenWidth(context: Context): Int {
        val outMetrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val outMetrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.heightPixels
    }

    fun getDeviceID(activity: Context): String? {
        return Settings.Secure.getString(activity.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun pxToDp(px: Int, c: Context): Int {
        val displayMetrics = c.resources.displayMetrics
        return Math.round(px * (displayMetrics.ydpi / DisplayMetrics.DENSITY_DEFAULT))
    }


//    fun showLoadingDialog(context: Context) {
//        if (progressDialog != null) {
//            progressDialog!!.dismiss()
//            progressDialog = null
//        }
//        progressDialog = LoadingDialog.newInstance(context)
//        progressDialog!!.setCanceledOnTouchOutside(false)
//        progressDialog!!.setCancelable(false)
//        progressDialog!!.show()
//        if (progressDialog!!.window != null) {
//            progressDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//            progressDialog!!.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        }
//    }
//
//    fun dismissLoadingDialog() {
//        if (progressDialog != null) {
//            progressDialog!!.dismiss()
//            progressDialog = null
//        }
//    }
}