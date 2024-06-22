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
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
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
        trueButton = findViewById(R.id.true_button)
        trueButton.setOnClickListener { view: View ->
        Toast.makeText(this,
            R.string.correct_toast,
            Toast.LENGTH_SHORT,
            ).show()
        // This part is developed as a challenge part.
        Snackbar.make(view,
            R.string.correct_toast,
            Snackbar.LENGTH_SHORT
        ).show();

        }
        falseButton = findViewById(R.id.false_button)

        falseButton.setOnClickListener { view: View ->
            Toast.makeText(this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            ). show()
            // This part is developed as a challenge part.
            Snackbar.make(view,
                R.string.incorrect_toast,
                Snackbar.LENGTH_SHORT
            ).show();
        }

    }
}