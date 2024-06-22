package com.example.geoquizz

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquizz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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
        enableEdgeToEdge()
        // Like the R class, View Binding generates code within your package structure,
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // this is a part of the challenge.
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1 ) % questionBank.size
            updateQuestion()
        }
        binding.trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        binding.falseButton.setOnClickListener { view: View ->
             checkAnswer(false)
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.previousButton.setOnClickListener {
            currentIndex = (currentIndex - 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()



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
}