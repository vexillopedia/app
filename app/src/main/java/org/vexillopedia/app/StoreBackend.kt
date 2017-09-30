package org.vexillopedia.app

import android.app.Application
import android.text.TextUtils
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class StoreBackend : Application() {
    companion object {
        private val TAG = StoreBackend::class.java.simpleName

        @get:Synchronized
        var instance: StoreBackend? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val requestQueue : RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>, tag: String) {
        request.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(request)
    }
}