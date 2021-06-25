package com.example.android.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
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
            binding.infoTv.text=getString(R.string.first_turn)
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
            btn.setTextColor(ContextCompat.getColor(this,R.color.black))
            binding.infoTv.text=getString(R.string.second_turn)
        }
        else{
            btn.text="O"
            btn.setTextColor(ContextCompat.getColor(this,R.color.white))
            binding.infoTv.text=getString(R.string.first_turn)
        }
        if(anyoneHasWon() or hasDrawn()){
            if(anyoneHasWon()){
                binding.infoTv.text=if(counter%2==0){
                    getString(R.string.first_won)
                }
                else{getString(R.string.second_won) }

            }
            else {
                binding.infoTv.text=getString(R.string.game_drawn)
            }

            //Calling the pop up activity to display the result
            val intent=Intent(this,PopActivity::class.java)
            intent.putExtra("Message",binding.infoTv.text)
            startActivity(intent)

            for(i in board){
                for (cell in i){
                    cell.isEnabled=false
                }
            }
        }
        btn.isEnabled=false
        counter+=1
    }


    /**
     * function which checks if any player has won
     */
    private  fun anyoneHasWon():Boolean{
        for(i in 0..2){
            /* checking if rows are same */if(board[i][0].text==board[i][1].text && board[i][2].text==board[i][1].text && board[i][0].text==board[i][2].text &&(board[i][0].text=="X" || board[i][0].text=="O")){return true}
            /* checking if columns are same */if(board[0][i].text==board[1][i].text && board[2][i].text==board[1][i].text && board[0][i].text==board[2][i].text &&(board[0][i].text=="X" || board[0][i].text=="O")){return true}
        }
        /* Checking if diagonal are same */
        if(board[0][0].text==board[1][1].text && board[2][2].text==board[1][1].text && board[0][0].text==board[2][2].text &&(board[0][0].text=="X" || board[0][0].text=="O")){return true}
        if(board[2][0].text==board[1][1].text && board[0][2].text==board[1][1].text && board[2][0].text==board[0][2].text &&(board[2][0].text=="X" || board[2][0].text=="O")){return true}
        return false
    }

    /**
     * function to check if game has drawn
     */
    private fun hasDrawn():Boolean{
        for(i in board){
            for (btn in i){
                if(btn.text!="X" && btn.text!="O"){return false}
            }
        }
        return true
    }
}