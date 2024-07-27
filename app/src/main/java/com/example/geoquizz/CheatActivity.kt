package com.example.geoquizz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquizz.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquizz.answer_is_true"

// This is used to send back the data.
const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

class CheatActivity : AppCompatActivity() {
	private lateinit var binding: ActivityCheatBinding
	private var answerIsTrue = false
	
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCheatBinding.inflate(layoutInflater)
		setContentView(binding.root)
		
		answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
		// read the android api number.
		binding.showApiVersion.text = Build.VERSION.SDK_INT.toString()
		
		binding.showAnswerButton.setOnClickListener {
			// get the button text from the intent.
			val answerText = when {
				answerIsTrue -> R.string.true_button
				else -> R.string.false_button
			}
			// set the button text.
			binding.answerTextView.setText(answerText)
			setAnswerShownResult(true)
		}
	}
	
	//A companion object allows you to access functions without having an instance of a class,
	// similar to static functions in Java.
	companion object {
		fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
			return Intent(packageContext, CheatActivity::class.java).putExtra(
				EXTRA_ANSWER_IS_TRUE,
				answerIsTrue
			)
		}
	}
	
	private fun setAnswerShownResult(isAnswerShown: Boolean) {
		val data = Intent().apply {
			putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
		}
		// result code is one of two predefined constants: Activity.RESULT_OK or
		// Activity.RESULT_CANCELED. (You can use another constant, RESULT_FIRST_USER,
		// as an offset when defining your own result codes.)
		// This result codes help the parent activity behaves differently based on issues happened
		// in the child activity. If setResult(...) is not called, then when the user presses
		// the Back button, the parent will receive Activity.RESULT_CANCELED.
		setResult(Activity.RESULT_OK, data)
	}
}