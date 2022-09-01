package com.tutorial.myapplication

import androidx.lifecycle.ViewModel
import java.util.*

class CrimeListViewModel : ViewModel() {
    val crimes = mutableListOf<Crime>()
    init {
        for (i in 0 until 15) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title ="Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0,
                requiresPolice = when ((0..1).shuffled().first()) {
                        0 -> false
                        else -> true
                    }
            )
            crimes += crime
        }
    }
}