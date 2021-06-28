package com.core.lib.common.retrofit.data.apiwrapper

import com.core.lib.common.retrofit.data.CoreModel
import com.lixi.libcore.common.retrofit.data.apierror.ErrorModel

/**
 * data base dang object
 */
class ApiWrapperModel<T> : CoreModel() {
    var meta: MetaDataModel? = null
    var data: T? = null
    var error: ErrorModel? = null


}