package org.vexillopedia.app

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class Store : StoreInterface {
    val TAG = Store::class.java.simpleName
    val baseURL = "https://www.vexillopedia.org/api"

    override fun request(method: Int, path: String, params: JSONObject, handler: (response: JSONObject?) -> Unit) {
        Log.d(TAG, "making request $method to $path ...")
        val jsonObjReq = object : JsonObjectRequest (method, baseURL + path, params,
                Response.Listener<JSONObject> { response ->
                    Log.d(TAG, "request OK! Response: $response")
                    handler(response)
                },
                Response.ErrorListener { error ->
                    Log.e(TAG, "request ERROR! Error: ${error.message}")
                    handler(null)
                }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Content-Type", "application/json")
                return headers
            }
        }

        StoreBackend.instance?.addToRequestQueue(jsonObjReq, TAG)
    }
}