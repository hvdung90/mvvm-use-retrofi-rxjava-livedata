package com.core.lib.common.base.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.annotation.FloatRange
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.core.lib.common.base.model.IInitView
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragmentDialog<VM : ViewModel, B : ViewDataBinding> : DialogFragment(),
    IInitView {
    lateinit var binding: B
    protected abstract val mViewModel: VM

    protected var compositeDisposable: CompositeDisposable = CompositeDisposable()

    @get:LayoutRes
    protected abstract val layoutRes: Int

    open fun isPrivateState(): Boolean {
        return true
    }

    open fun getListBroadcast(): List<String> {
        return ArrayList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!::binding.isInitialized) {
            binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        }
        dialog?.setCanceledOnTouchOutside(true)
        dialog?.setCancelable(true)
        return binding?.root
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

    //Show new dialog if dialog is not showing, otherwise show instance
    fun showInstance(manager: FragmentManager?, tag: String?) {
        super.show(manager!!, tag)
    }

    /**
     * Set dim background, should call in onStart method
     *
     * @param dimBehind percent of dimAmount
     */
    protected fun setDimBehind(@FloatRange(from = 0.0, to = 1.0) dimBehind: Float) {
        val window = dialog!!.window
        if (window != null) {
            val windowParams = window.attributes
            windowParams.dimAmount = dimBehind
            windowParams.flags = windowParams.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
            window.attributes = windowParams
        }
    }

    /**
     * Set with and height, should call in onStart method
     *
     * @param width  width size
     * @param height height size
     */
    protected fun setSize(width: Int, height: Int) {
        val window = dialog!!.window
        if (window != null) {
            val windowParams = window.attributes
            windowParams.gravity = Gravity.CENTER
            windowParams.width = width
            windowParams.height = height
            window.attributes = windowParams
        }
    }

    /**
     * Set with and height, should call in onStart method
     *
     * @param width  width size
     * @param height height size
     */
    protected fun setSizeTop(width: Int, height: Int) {
        val window = dialog!!.window
        if (window != null) {
            val windowParams = window.attributes
            windowParams.gravity = Gravity.TOP
            windowParams.width = width
            windowParams.height = height
            window.attributes = windowParams
        }
    }


    /**
     * Set this function on onCreate method to apply no title dialog
     *
     * @param theme set theme or put 0 to create normal dialog
     */
    protected fun supportNoTitle(@StyleRes theme: Int) {
        setStyle(STYLE_NO_TITLE, theme)
    }

    /**
     * Set transparent background
     */
    protected fun supportTransparentBackground() {
        if (dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }


    /**
     * Dialog will resize (height) when SoftKeyBoard is showing, this function will make this happen
     * won't active. Should call on view created
     */
    protected fun supportNoResize() {
        if (dialog!!.window != null) {
            dialog!!.window!!.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
            )
        }
    }

    companion object {
        //Use to dismiss dialog fragment with tag
        fun dismissInstance(manager: FragmentManager, tag: String?) {
            val ft = manager.beginTransaction()
            val prev = manager.findFragmentByTag(tag) as BaseFragmentDialog<*, *>?
            if (prev != null) {
                prev.dismiss()
                ft.remove(prev)
                ft.addToBackStack(null)
                ft.commit()
            }
        }
    }
}