package com.example.geoquizz

import android.util.Log
import androidx.lifecycle.ViewModel


private const val TAG = "QuizViewModel"
class QuizViewModel: ViewModel() {
    init {
        Log.d(TAG, "View model instance created.")
    }
    // This method is used before any clean up.
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "View model instance is about to be destroyed")
    }

}