package com.example.geoquizz

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
/// This RunWith annotation signals that this test should run on
/// Android device. To run this tests you need to connect an android device
/// Or emulator.
// Instrumented tests are more time consuming compared to JVM tests on ViewModel.
// Also ViewModel is well defined to receive input and deliver output which makes it more
// testable.
// This testability is one of the reasons that it has been advised to keep business logic
// in ViewModel and not activity.
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.geoquizz", appContext.packageName)
    }
    
}