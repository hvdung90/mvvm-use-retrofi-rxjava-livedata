package com.core.lib.common.base.viewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel() {
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
        disposable.clear()
    }
}