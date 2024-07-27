package com.example.geoquizz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStore
import com.example.geoquizz.databinding.ActivityMainBinding


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	
	// by indicates that property is implemented using a
	// property delegate. Another famous property delegate
	// is lazy. It allows developers to save resources by
	// waiting to initialize property when they are accessed.
	private val quizViewModel: QuizViewModel by viewModels()
	
	// When you want to hear back from the child activity, you register your MainActivity for
	// an ActivityResult using the Activity Results API. The Activity Results API is different
	// from other APIs you have interacted with so far within the Activity class.
	// Instead of overriding a lifecycle method, you will initialize a class property within
	// your MainActivity using the registerForActivityResult() function. That function takes in
	// two parameters:
	// 1. contract that defines the input and output of the Activity you are trying to start.
	// 2. a lambda in which you parse the output that is returned.
	
	private val cheatLauncher = registerForActivityResult(
		//  It is a basic contract that takes in an Intent as input and provides
		//  an ActivityResult as output. you can define a custom contract or use other contracts.
		ActivityResultContracts.StartActivityForResult()
	) { result ->
		if (result.resultCode == Activity.RESULT_OK) {
			/// Based on the result we can know if user has cheated or not.
			quizViewModel.isCheater =
				result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
		}
		
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		Log.d(TAG, "onCreate(Bundle?) called");
		// Like the R class, View Binding generates code within your package structure,
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		// The reason that we see Java statement in the cat log is the fact kotlin exceptions
		// mapped to java.lang exceptions through type-aliasing.
		Log.d(TAG, "got a quizViewModel: $quizViewModel")
		// this is a part of the challenge.
		binding.questionTextView.setOnClickListener {
			enableButtons(false)
			quizViewModel.moveToNext()
			updateQuestion()
			enableButtons(true)
		}
		binding.trueButton.setOnClickListener { view: View ->
			enableButtons(false)
			checkAnswer(true)
			enableButtons(true)
		}
		
		binding.falseButton.setOnClickListener { view: View ->
			enableButtons(false)
			checkAnswer(false)
			enableButtons(true)
		}
		
		binding.nextButton.setOnClickListener {
			enableButtons(false)
			quizViewModel.moveToNext()
			updateQuestion()
			enableButtons(true)
		}
		
		binding.cheatButton.setOnClickListener {
			// start CheatActivity.
			// We should ask OS to create a new activity .
			// Intent is an object that a component use to communicate with OS.
			// The Context argument tells the ActivityManager which application package
			// the activity class can be found in.
			// The Class argument you pass to the Intent constructor specifies the activity class
			// that the ActivityManager should start
			
			// New approach that uses companion object.
			val answerIsTrue = quizViewModel.currentQuestionAnswer
			val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
			cheatLauncher.launch(intent)
			// Old approach before using companion object
			// val intent = Intent(this, CheatActivity::class.java)
			// startActivity(intent)
			
			// Before starting the activity, the ActivityManager checks the package’s manifest for
			// a declaration with the same name as the specified Class. If it finds a declaration,
			// it starts the activity, and all is well. If it does not, you get a nasty
			// ActivityNotFoundException, which will crash your app.
			// This is why all your activities must be declared in the manifest.
			// Explicit Intent: start an activity in the same app.
			// Implicit Intent: start an activity in another app.
		}
		
		binding.previousButton.setOnClickListener {
			quizViewModel.moveToPrevious()
			updateQuestion()
		}
		
		updateQuestion()
		
		
	}
	
	private fun enableButtons(enabled: Boolean) {
		binding.previousButton.isEnabled = enabled
		binding.nextButton.isEnabled = enabled
		binding.falseButton.isEnabled = enabled
		binding.trueButton.isEnabled = enabled
		
	}
	
	private fun updateQuestion() {
		val questionTextResId = quizViewModel.currentQuestionText
		binding.questionTextView.setText(questionTextResId)
	}
	
	private fun checkAnswer(userAnswer: Boolean) {
		val answer = quizViewModel.currentQuestionAnswer
		
		
		val messageResId = when {
			quizViewModel.isCheater -> R.string.judgment_toast
			userAnswer == answer -> R.string.correct_toast
			else -> R.string.incorrect_toast
			
		}
		
		Toast.makeText(
			this,
			messageResId,
			Toast.LENGTH_SHORT
		).show()
		// This part is developed as a challenge part.
	}
	
	
	override fun onStart() {
		super.onStart()
		Log.d(TAG, "onStart() called")
	}
	
	override fun onResume() {
		super.onResume()
		Log.d(TAG, "onResume() called")
	}
	
	override fun onPause() {
		super.onPause()
		Log.d(TAG, "onPause() called")
	}
	
	override fun onStop() {
		super.onStop()
		Log.d(TAG, "onStop() called")
	}
	
	override fun onDestroy() {
		super.onDestroy()
		Log.d(TAG, "onDestroy() called")
	}
}