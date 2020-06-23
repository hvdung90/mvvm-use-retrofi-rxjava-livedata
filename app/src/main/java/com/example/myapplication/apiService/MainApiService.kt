package com.example.myapplication.apiService

import com.betall.lib.common.retrofit.data.apiwrapper.ApiWrapperForListModel
import com.example.myapplication.model.ReportModel
import io.reactivex.Flowable
import retrofit2.http.*

interface MainApiService {
    @GET("api/mobile/report/liabilities")
    fun getReport(
        @Query("fromDate") fromDate: String,
        @Query("toDate") toDate: String,
        @Query("userCode") userCode: String
    ): Flowable<ApiWrapperForListModel<ReportModel>>
}