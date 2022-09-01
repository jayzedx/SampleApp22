package com.tutorial.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(var name : String) : Parcelable {
}