package com.tutorial.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.tutorial.myapplication.databinding.ActivityMainBinding
import javax.security.auth.callback.Callback

private const val EXTRA_DATA = "com.tutorial.myapplication.extra_data"
private const val REQUEST_CODE_DATA = 0
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activityButton.setOnClickListener {
            val intent = MainActivity.newIntent(this@MainActivity, callback)
            //startActivity(intent)
            secondLauncher.launch(intent)
        }
    }

    val callback = Listener {
        Log.d(TAG, "callback is called")
    }

    val secondLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK)
            result.data?.getParcelableExtra<User>(EXTRA_DATA)?.let {
                Log.d(TAG, it.name)
            }
    }

    companion object {
        fun newIntent(packageContext: Context, data: Parcelable): Intent {
            return Intent(packageContext, SecondActivity::class.java).apply {
                putExtra(EXTRA_DATA, data)
            }
        }
    }
}