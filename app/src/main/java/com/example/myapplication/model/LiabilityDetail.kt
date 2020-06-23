package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class LiabilityDetail {
    @SerializedName("insuranceProductCategoryName")
    var insuranceProductCategoryName: String? = null

    @SerializedName("paid")
    var paid: Long? = null

    @SerializedName("period")
    var period: Long? = null

    @SerializedName("unpaid")
    var unpaid: Long? = null

}