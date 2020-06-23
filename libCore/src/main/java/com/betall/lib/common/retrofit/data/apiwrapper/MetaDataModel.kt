package com.betall.lib.common.retrofit.data.apiwrapper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by vutha_000 on 3/1/2018.
 */
class MetaDataModel : Serializable {
    @SerializedName("syncDate")
    @Expose
    var syncDate: String? = null
}