package com.android.fundsapp.utils


import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class SimpleIdeResource : IdlingResource {

    private var callback: IdlingResource.ResourceCallback? = null
    private val isIdleNow = AtomicBoolean(true)


    override fun getName(): String {
        return this::class.java.simpleName
    }


    override fun isIdleNow(): Boolean {
        return isIdleNow.get()
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback) {
        this.callback = callback
    }

    fun setIdleState(state: Boolean) {
        isIdleNow.set(state)
        if (callback != null) {
            callback!!.onTransitionToIdle()
        }
    }
}
