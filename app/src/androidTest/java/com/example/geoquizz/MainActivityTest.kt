package com.example.geoquizz

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
	/// ActivityScenario is a container for your MainActivity.
	private lateinit var scenario: ActivityScenario<MainActivity>
	@Before
	fun setUp() {
		// create a fresh instance of MainActivity before each test.
		scenario = ActivityScenario.launch(MainActivity:: class.java)
	}
	@After
	fun tearDown() {
		// close the started instance after each test.
		scenario.close()
	}
	
	@Test
	fun showFirstQuestionOnLaunch(){
		// View matcher: onView(withId()). This part finds the iew.
		// View assertion: check(matches()). verifies the behavior you are interested in.
		onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_australia)))
		
	}
	
	@Test
	fun showSecondQuestionAfterNextPress(){
		onView(withText(R.string.next_button)).perform(click())
		// Ths function tears down and rebuilds MainActivity again. Like scenarios in which
		// device rotates.
		scenario.recreate()
		onView(withId(R.id.question_text_view)).check(matches(withText(R.string.question_oceans)))
	}
	

}