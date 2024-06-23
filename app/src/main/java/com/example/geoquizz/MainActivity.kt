package com.example.geoquizz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquizz.databinding.ActivityMainBinding


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentIndex: Int =0;

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called");
        // Like the R class, View Binding generates code within your package structure,
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // this is a part of the challenge.
        binding.questionTextView.setOnClickListener {
            enableButtons(false)
            currentIndex = (currentIndex + 1 ) % questionBank.size
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
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            enableButtons(true)
        }

        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
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
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val answer = questionBank[currentIndex].answer

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