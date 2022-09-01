package com.tutorial.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Listener(val clickAction: () -> Unit) : Parcelable {
    fun onClick() = clickAction()
}