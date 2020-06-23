package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import com.betall.lib.common.base.viewModel.BaseViewModel
import com.example.myapplication.apiRepository.MainRepository
import com.example.myapplication.model.ReportModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainViewModel @Inject constructor(private var repository: MainRepository) : BaseViewModel() {
    val reports: MutableLiveData<List<ReportModel>> = MutableLiveData<List<ReportModel>>()
    val reportError = MutableLiveData<Boolean>()

    fun getReport(
        fromDate: String,
        toDate: String,
        userCode: String
    ) {
        disposable.add(
            repository.getReport(fromDate, toDate, userCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    reports.value = it.data
                    reportError.value = false
                }, {
                    reportError.value = true
                })
        )
    }
}