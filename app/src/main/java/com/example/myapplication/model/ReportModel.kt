package com.example.myapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class ReportModel :Serializable{
    @SerializedName("liabilityDetails")
    var liabilityDetails: List<LiabilityDetail>? = null

    @SerializedName("period")
    var period: Long? = null

    @SerializedName("total")
    var total: Long? = null

    @SerializedName("totalPaid")
    var totalPaid: Long? = null

    @SerializedName("totalUnpaid")
    var totalUnpaid: Long? = null

}