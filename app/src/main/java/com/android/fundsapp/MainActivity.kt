package com.android.fundsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.fundsapp.fundslist.FundsRepository
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: FundsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppApplication.getMainComponent().inject(this)

        repository.getFunds()
    }
}
