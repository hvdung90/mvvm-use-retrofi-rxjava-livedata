package com.core.lib.common.base.ui

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import com.core.lib.R
import com.core.lib.common.base.model.IInitView
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by Dunghv
 * BaseActivity: cac activity extends lai tu base va truyen viewmodel, ViewDataBinding
 */
abstract class BaseActivity<VM : ViewModel, B : ViewDataBinding> : AppCompatActivity(), IInitView {
    //quan ly va xoa cac su khi hoan thanh hoac man hinh chet
    var compositeDisposable: CompositeDisposable? = CompositeDisposable()
    protected abstract val mViewModel: VM
    lateinit var binding: B

    @LayoutRes
    abstract fun getLayoutRes(): Int

    open fun getListBroadcast(): List<String> {
        return ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutRes())

        init()
        overridePendingTransition(R.anim.trans_left_in, R.anim.not_move)
    }

    public override fun onDestroy() {
        super.onDestroy()
        try {
            if (compositeDisposable != null) compositeDisposable?.dispose()
        } catch (e: Exception) {
            e.fillInStackTrace()
        }
    }

    override fun onBackPressed() {
        try {
            super.onBackPressed()
            overridePendingTransition(R.anim.not_move, R.anim.trans_right_out)
            var cn = ComponentName(this, ".MainActivity")
            var cnLogin = ComponentName(this, ".LoginActivity")
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