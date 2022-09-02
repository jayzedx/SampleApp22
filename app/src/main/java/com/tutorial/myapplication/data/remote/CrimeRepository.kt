package com.tutorial.myapplication.data.remote

import android.content.Context
import com.tutorial.myapplication.Crime
import com.tutorial.myapplication.data.local.CrimeDao
import com.tutorial.myapplication.data.local.CrimeDatabase

class CrimeRepository  {
    private val db = CrimeDatabase.getInstance()

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun getInstance(): CrimeRepository {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository()
            }
            return INSTANCE as CrimeRepository
        }
    }

    suspend fun getCrimes(): List<Crime> = db.crimeDao().getCrimes()
//    suspend fun getCrime(id: UUID): Crime = db.crimeDao().getCrime(id)
}