package com.mudassir.galleryapp.ui.base

import android.view.WindowManager
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.mudassir.galleryapp.extensions.invisible
import com.mudassir.galleryapp.extensions.visible

abstract class BaseFragment : Fragment() {

    protected var progressBar: ProgressBar? = null


    //region Progress bar
    protected fun showProgress(showProgress: Boolean, lockScreen: Boolean) {
        if (showProgress) {
            progressBar?.visible()

        } else {
            progressBar?.invisible()

        }
        if (lockScreen) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }


}