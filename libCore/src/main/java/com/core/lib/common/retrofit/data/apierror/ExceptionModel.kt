package com.lixi.libcore.common.retrofit.data.apierror

import java.io.Serializable

/**
 * Created by vutha_000 on 3/1/2018.
 */
class ExceptionModel : Serializable {
    var name: String? = null
    var type: String? = null
    var arg: ArgErrorModel? = null

}