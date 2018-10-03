package com.example.yashg.kennygame

import android.content.DialogInterface
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var score: Int = 0
    var imageArray=ArrayList<ImageView>()
    var handler:Handler=Handler()
    var runnable:Runnable= Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        score = 0

        imageArray = arrayListOf(imageView1, imageView2, imageView3, imageView4, imageView5, imageView6)

        var flag=true


            doProcess()




    }

    fun doProcess(){
        hideImages()
        object : CountDownTimer(10000, 1000) {
            override fun onFinish() {
                textView.text = "Time Off"
                handler.removeCallbacks(runnable)
                for (mgs in imageArray){
                    mgs.visibility=View.INVISIBLE
                }
                val alert=AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Do you want to play Again??")
                alert.setMessage("Are you Sure")
                alert.setPositiveButton("Yes"){ dialog: DialogInterface, which: Int -> run{

                    doProcess()
                }}
               alert.setNegativeButton("No"){dialog: DialogInterface, which: Int -> ""}//Toast.makeText(this@MainActivity,"Great",Toast.LENGTH_LONG).show()
                alert.show()
            }

            override fun onTick(millisUntilFinished: Long) {
                var x = millisUntilFinished / 1000
                textView.text = x.toString()
            }
        }.start()
    }


    fun increasescore(view: View) {
            score++
            textView2.text = "Score" + score
        }

        fun hideImages(){
            runnable=object : Runnable{
                override fun run() {
                    for (imgs in imageArray){
                        imgs.visibility=View.INVISIBLE
                    }
                    val random=Random()
                    val index=random.nextInt(5)
                    imageArray[index].visibility=View.VISIBLE
                    handler.postDelayed(this,2000)
                }

            }
            handler.post(runnable)
        }
    }

