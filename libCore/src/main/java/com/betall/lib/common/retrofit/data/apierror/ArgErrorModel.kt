package com.lixi.libcore.common.retrofit.data.apierror

import java.io.Serializable
import java.util.*

/**
 * Created by vutha_000 on 3/1/2018.
 */
class ArgErrorModel : Serializable {
    var message: String? = null
    var args: ArrayList<String>? = null

}