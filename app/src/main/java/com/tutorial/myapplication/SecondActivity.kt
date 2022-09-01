package com.tutorial.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.tutorial.myapplication.databinding.ActivitySecondBinding

private const val EXTRA_DATA = "com.tutorial.myapplication.extra_data"
private const val TAG = "SecondActivity"
private const val REQUEST_CODE_DATA = 0

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

       intent.getParcelableExtra<Listener>(EXTRA_DATA)?.let { callback ->
           callback.onClick()
           setShownResult()
       }
    }

    private fun setShownResult() {
        val data = Intent().apply {
            putExtra(EXTRA_DATA, User(name = "jayzedx"))
        }
        setResult(Activity.RESULT_OK, data)
    }
}