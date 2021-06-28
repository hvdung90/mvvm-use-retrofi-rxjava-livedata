package com.core.lib.common.retrofit.data.apiwrapper

import com.core.lib.common.retrofit.data.CoreModel
import com.lixi.libcore.common.retrofit.data.apierror.ErrorModel
import java.util.*

/**
 * data base dang list object
 */
open class ApiWrapperForListModel<T> : CoreModel() {
    var meta: MetaDataModel? = null
    var data: ArrayList<T>? = null
    var error: ErrorModel? = null
}