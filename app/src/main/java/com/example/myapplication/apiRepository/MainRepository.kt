package com.example.myapplication.apiRepository

import com.betall.lib.common.retrofit.data.apiwrapper.ApiWrapperForListModel
import com.betall.lib.utils.Utils
import com.example.myapplication.apiService.MainApiService
import com.example.myapplication.model.ReportModel
import io.reactivex.Flowable
import javax.inject.Inject


/**
 * Created by Dunghv
 */
class MainRepository @Inject internal constructor(private val apiService: MainApiService) {

    fun getReport(
        fromDate: String,
        toDate: String,
        userCode: String
    ): Flowable<ApiWrapperForListModel<ReportModel>> {
        return apiService.getReport(fromDate, toDate, userCode)
            .onErrorReturn { Utils.getInstance().handleApiReviewForListModelError(it) }
    }

}