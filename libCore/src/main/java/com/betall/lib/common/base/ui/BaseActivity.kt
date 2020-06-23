package com.betall.lib.common.base.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.betall.lib.R
import com.betall.lib.common.base.model.IInitView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by Dunghv
 * BaseActivity: cac activity extends lai tu base va truyen viewmodel, ViewDataBinding
 */
abstract class BaseActivity<V : ViewModel, B : ViewDataBinding> : AppCompatActivity(),
    HasAndroidInjector, IInitView {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    //quan ly va xoa cac su khi hoan thanh hoac man hinh chet
    var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    lateinit var binding: B
    lateinit var viewModel: V

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract fun getViewModel(): Class<V>?

    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun getListBroadcast(): List<String> {
        return ArrayList()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            AndroidInjection.inject(this)
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        viewModel = ViewModelProvider(viewModelStore, viewModelFactory).get(getViewModel()!!)

        init()
        overridePendingTransition(R.anim.trans_left_in, R.anim.not_move)
    }

    public override fun onDestroy() {
        super.onDestroy()
        try {
            if (compositeDisposable != null) compositeDisposable!!.dispose()
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
    }

    override fun onBackPressed() {
        try {
            super.onBackPressed()
            overridePendingTransition(R.anim.not_move, R.anim.trans_right_out)
            var cn = ComponentName(this, "com.lixi.client.home.ui.activity.MainActivity")
            var cnLogin = ComponentName(this, "com.lixi.libaccount.ui.LoginActivity")
            if (isTaskRoot && componentName != cn && cnLogin != componentName) {
                val intent = Intent().setComponent(cn)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        } catch (e: Exception) {

        }
    }

    open fun getTransaction(): FragmentTransaction? {
        if (isFinishing) {
            return null
        }
        val ft: FragmentTransaction = supportFragmentManager
            .beginTransaction()
        ft.setCustomAnimations(
            R.anim.trans_left_in,
            R.anim.not_move,
            R.anim.not_move,
            R.anim.trans_right_out
        )
        return ft
    }
}