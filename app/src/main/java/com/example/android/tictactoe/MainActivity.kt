package com.example.android.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.android.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,  View.OnClickListener{

    //2D Matrix of buttons
    private lateinit var board : Array<Array<Button>>

    //Tracks which player turn it is
    private var counter=0

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        //Initialising binging class
        binding=ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Initialising playing board
        board= arrayOf(
            arrayOf(binding.btn1,binding.btn2,binding.btn3),
            arrayOf(binding.btn4,binding.btn5,binding.btn6),
            arrayOf(binding.btn7,binding.btn8,binding.btn9)
        )
        for(i in board){
            for(btn in i){
                btn.setOnClickListener(this)
            }
        }

        //setting the reset button
        binding.resetBtn.setOnClickListener {
            counter=0
            binding.infoTv.text="Player 1 Turn"
            for(i in board){
                for(btn in i){
                    btn.isEnabled=true
                    btn.text=""
                }
            }

        }

    }

    override fun onClick(v: View?){
        val btn:Button=findViewById(v!!.id)
        if(counter%2==0){
            btn.text="X"
            binding.infoTv.text="Player 1 turn"
        }
        else{
            btn.text="O"
            binding.infoTv.text="Player 2 turn"
        }
        btn.isEnabled=false
        counter+=1
    }
}