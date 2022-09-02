package com.tutorial.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorial.myapplication.data.remote.CrimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class CrimeListViewModel : ViewModel() {
    private val crimeRepository = CrimeRepository.getInstance()
    val crimes = mutableListOf<Crime>()
    init {
        //run async
        viewModelScope.launch {
//            delay(10)
//            for (i in 0 until 15) {
//                val crime = Crime(
//                    id = UUID.randomUUID(),
//                    title = "Crime #$i",
//                    date = Date(),
//                    isSolved = i % 2 == 0,
//                    requiresPolice = when ((0..1).shuffled().first()) {
//                        0 -> false
//                        else -> true
//                    }
//                )
//                crimes += crime
//            }
            crimes += loadCrimes()
        }
    }

    //run async
    suspend fun loadCrimes(): List<Crime> {

        val result = mutableListOf<Crime>()
        delay(10)
        for (i in 0 until 10) {
            val crime = Crime(
                id = UUID.randomUUID(),
                title = "Crime #$i",
                date = Date(),
                isSolved = i % 2 == 0,
                requiresPolice = when ((0..1).shuffled().first()) {
                    0 -> false
                    else -> true
                }
            )
            result += crime
        }

        return crimeRepository.getCrimes() + result
    }
}
