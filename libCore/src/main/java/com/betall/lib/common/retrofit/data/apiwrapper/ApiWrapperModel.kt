package com.betall.lib.common.retrofit.data.apiwrapper

import com.betall.lib.common.retrofit.data.CoreModel
import com.lixi.libcore.common.retrofit.data.apierror.ErrorModel
import java.io.Serializable

/**
 * data base dang object
 */
class ApiWrapperModel<T> : CoreModel() {
    var meta: MetaDataModel? = null
    var data: T? = null
    var error: ErrorModel? = null


}