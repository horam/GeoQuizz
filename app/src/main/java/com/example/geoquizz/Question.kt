package com.example.geoquizz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean)


/// The compiler automatically derives the following members from all properties
// declared in the primary constructor:
// 1_ .equals()/.hashCode() pair.
// 2_ .toString() of the form "User(name=John, age=42)".
// 3_ .componentN() functions corresponding to the properties in their order of declaration.
// 4_ .copy() function.