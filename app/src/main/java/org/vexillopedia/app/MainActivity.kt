package org.vexillopedia.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.android.volley.Request
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MESSAGE = "org.vexillopedia.app.MESSAGE"
    }

    val store : Store = Store()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        store.request(Request.Method.GET, "/flag/Spain", JSONObject()) { response ->
            Log.d("Spain", response.toString())
        }
    }

    fun sendMessage(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val editText = findViewById<View>(R.id.editText) as EditText
        val message = editText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }
}
