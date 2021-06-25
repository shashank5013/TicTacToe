package com.example.android.tictactoe

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import com.example.android.tictactoe.databinding.ActivityPopBinding

/**
 * Pop Activity which shows the result of the game whenever the game finishes
 */
class PopActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding=ActivityPopBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val message=intent.getStringExtra("Message")

        binding.popUp.text=message


        val dm=DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)

        val width=dm.widthPixels
        val height=dm.heightPixels

        window.setLayout((width*.8).toInt(),(height*.5).toInt())

        val params=window.attributes
        params.gravity=Gravity.CENTER
        params.x=0
        params.y=-20
        window.attributes=params

    }
}