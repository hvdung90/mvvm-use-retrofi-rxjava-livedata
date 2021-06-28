package com.core.lib.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.core.lib.common.base.model.IInitView
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Dunghv
 * BaseFragment: cac Fragment extends lai tu base va truyen viewmodel, ViewDataBinding
 */
abstract class BaseFragment<VM : ViewModel, B : ViewDataBinding> : Fragment(),
    IInitView {
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected abstract val mViewModel: VM
    lateinit var binding: B
    private var isInit: Boolean = false

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isInit) {
            init()
        }
        isInit = true
    }


    override fun onDestroy() {
        super.onDestroy()
        try {
            compositeDisposable.dispose()
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
    }
}