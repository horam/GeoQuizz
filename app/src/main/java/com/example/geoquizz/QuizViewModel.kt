package com.example.geoquizz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

// The relationship between MainActivity and QuizViewModel is unidirectional. The activity
// references the ViewModel, but the ViewModel does not access the activity.
// Your ViewModel should never hold a reference to an activity or a view, otherwise you will
// introduce a memory leak.
// A memory leak occurs when one object holds a strong reference to another object that should
// be destroyed. The strong reference prevents the garbage collector from clearing the object
// from memory. Memory leaks due to a configuration change are common bugs.
// (The details of strong reference and garbage collection are outside the scope of this book.
// If you are not sure about these concepts, we recommend reading up on them in a Kotlin or
// Java reference.)
// Your ViewModel instance stays in memory across rotation, while your original activity instance
// gets destroyed. If the ViewModel held a strong reference to the original activity instance,
// two problems would occur:
//
// First, the original activity instance would not be removed from memory,
// and thus the activity would be leaked.
//
// Second, the ViewModel would hold a reference to a stale activity. If the ViewModel tried to
// update the view of the stale activity, it would trigger an IllegalStateException.
private const val TAG = "QuizViewModel"

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    // to save the quiz state when we app goes to background for a long time.
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    // we move the presentation logic into the view to make the activity simple.
    val currentQuestionAnswer: Boolean get() = questionBank[currentIndex].answer

    val currentQuestionText: Int get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        // This way we can have a complete log cat message with this format. This technique
        // is verbose and can help others still your ideas, so do not use it until it is necessary.
        Log.d(TAG, "updating question text", Exception())
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1) % questionBank.size
    }



}
//** When you associate a ViewModel instance with an activity’s lifecycle, as you did in Listing 4.3,
// the ViewModel is said to be scoped to that activity’s lifecycle. This means the ViewModel will
// remain in memory, regardless of the activity’s state, until the activity is finished. Once the
// activity is finished (such as by the user closing the app from the overview screen),
// the ViewModel instance is destroyed.
//This means that the ViewModel stays in memory during a configuration change, such as rotation.
// During the configuration change, the activity instance is destroyed and re-created, but any
// ViewModels scoped to the activity stay in memory.
// So QuizViewModel is stays in this scenario but not the activity.

// Each app gets its wn process containing a single thread to execute UI work on a piece of memory.
// If app is in foreground it has a higher priority in compared to teh app in background.
// SO background apps will be destroyed after a while by the os. The only way to keep the state
// is to use saved instance state.

// view model and saved instance state are not solution for long term data storage,


