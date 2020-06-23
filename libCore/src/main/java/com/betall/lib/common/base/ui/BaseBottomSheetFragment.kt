package com.betall.lib.common.base.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.betall.lib.common.base.model.IInitView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Dunghv
 */
abstract class BaseBottomSheetFragment<V : ViewModel, B : ViewDataBinding> :
    BottomSheetDialogFragment(),
    IInitView, HasAndroidInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    var compositeDisposable = CompositeDisposable()
    lateinit var binding: B
    lateinit var viewModel: V

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract fun getViewModel(): Class<V>?

    @get:LayoutRes
    protected abstract val layoutRes: Int

    open fun isPrivateState(): Boolean {
        return true
    }

    open fun getListBroadcast(): List<String> {
        return ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            AndroidSupportInjection.inject(this)
            viewModel = ViewModelProvider(
                if (isPrivateState()) requireParentFragment().viewModelStore else requireActivity().viewModelStore,
                viewModelFactory
            ).get(getViewModel()!!)

        } catch (e:Exception) {
            e.fillInStackTrace()
        }
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