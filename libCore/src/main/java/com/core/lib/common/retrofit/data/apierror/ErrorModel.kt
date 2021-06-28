package com.lixi.libcore.common.retrofit.data.apierror

import android.text.TextUtils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Dunghv on 6/27/2019
 */
class ErrorModel {
    @Expose
    @SerializedName("stack")
    private val stack: String? = null
    @Expose
    @SerializedName("data")
    var data: Object? = null
    @Expose
    @SerializedName("msg")
    var msg: String? = null
        get() = if (TextUtils.isEmpty(field)) message else field
    @Expose
    @SerializedName("code")
    var code: String? = null
    @Expose
    @SerializedName("exception")
    var exception: Exception? = null
    @Expose
    @SerializedName("message")
    var message: String? = null
    @Expose
    @SerializedName("timestamp")
    var timestamp: String? = null
    @Expose
    @SerializedName("level")
    var level: String? = null

}