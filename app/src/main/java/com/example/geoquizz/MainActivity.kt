package com.example.geoquizz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStore
import com.example.geoquizz.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // by indicates that property is implemented using a
    // property delegate. Another famous property delegate
    // is lazy. It allows developers to save resources byx
    // waiting to initialize property when they are accessed.
    private val quizViewModel: QuizViewModel by viewModels ()

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

        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        updateQuestion()



    }

    private fun enableButtons(enabled: Boolean){
        binding.previousButton.isEnabled = enabled
        binding.nextButton.isEnabled = enabled
        binding.falseButton.isEnabled = enabled
        binding.trueButton.isEnabled = enabled

    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val answer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == answer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }

        Toast.makeText(this,
            messageResId,
            Toast.LENGTH_SHORT
        ). show()
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