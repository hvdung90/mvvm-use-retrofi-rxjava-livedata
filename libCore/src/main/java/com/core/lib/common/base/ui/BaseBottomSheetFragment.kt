package com.core.lib.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.core.lib.common.base.model.IInitView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Dunghv
 */
abstract class BaseBottomSheetFragment<VM : ViewModel, B : ViewDataBinding> :
    BottomSheetDialogFragment(),
    IInitView {
    var compositeDisposable = CompositeDisposable()
    lateinit var binding: B
    protected abstract val mViewModel: VM

    @get:LayoutRes
    protected abstract val layoutRes: Int

    open fun getListBroadcast(): List<String> {
        return ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setCancelable(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        try {
            compositeDisposable.dispose()
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
    }
}