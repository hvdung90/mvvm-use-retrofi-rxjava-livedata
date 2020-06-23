package com.betall.lib.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.betall.lib.common.base.model.IInitView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by Dunghv
 * BaseFragment: cac Fragment extends lai tu base va truyen viewmodel, ViewDataBinding
 */
abstract class BaseFragment<V : ViewModel, B : ViewDataBinding> : Fragment(), HasAndroidInjector,
    IInitView {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var binding: B
    lateinit var viewModel: V
    private var isInit: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract fun getViewModel(): Class<V>?

    @get:LayoutRes
    protected abstract val layoutRes: Int

    /*
    * false viewModel khai bao 1 lần ở activity || true viewModel dung rieng cho tung fragment
    * */
    open fun isPrivateState(): Boolean {
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            AndroidSupportInjection.inject(this)
            viewModel = ViewModelProvider(
                if (isPrivateState()) viewModelStore else requireActivity().viewModelStore,
                viewModelFactory
            ).get(getViewModel()!!)
        } catch (e: java.lang.Exception) {
            e.fillInStackTrace()
        }
        super.onCreate(savedInstanceState)

    }

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