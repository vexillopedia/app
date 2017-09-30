package org.vexillopedia.app

import org.json.JSONObject


interface StoreInterface {
    fun request(method: Int, path: String, params: JSONObject, handler: (response: JSONObject?) -> Unit)
}