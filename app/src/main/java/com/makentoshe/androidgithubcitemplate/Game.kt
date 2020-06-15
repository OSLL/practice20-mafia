package com.makentoshe.androidgithubcitemplate

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.game.*

import com.makentoshe.androidgithubcitemplate.mechanic.Main

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Game : AppCompatActivity() {
    lateinit var arrayBtn: Array<Button>
    private val arrayPm = ArrayList<PopupMenu>(8)
    private var nameArray = Array<String>(8){""}
    lateinit var main: Main
    var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        Log.d("game", "Game is here")

        history.movementMethod = ScrollingMovementMethod()
        arrayBtn = arrayOf(button0, button1, button2, button3, button4, button5, button6, button7)

        for (btn in arrayBtn)
            createPopup(btn, arrayPm)

        val playerCounter = intent.getStringExtra("cnt").toString().toInt()
        val angle = 360 / playerCounter
        var param: ConstraintLayout.LayoutParams

        for (i in 0 until playerCounter) {
            nameArray[i] = intent.getStringExtra("$i").toString()
            arrayBtn[i].visibility = View.VISIBLE
            param = arrayBtn[i].layoutParams as ConstraintLayout.LayoutParams
            param.circleAngle = i * angle.toFloat()
            arrayBtn[i].layoutParams = param
        }


        start.setOnClickListener {
            if (!isClicked) {
                Toast.makeText(this, "Choose smb!", Toast.LENGTH_SHORT).show()
            } else {
                main.startStep()
                isClicked = false;
            }
        }

        exit.setOnClickListener {
            var myIntent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(myIntent)
        }

        main = Main(history, arrayBtn, arrayPm, this.icon, this.state, this.exit, this.start, nameArray, playerCounter, scroll)
    }

    private fun createPopup(btn: Button, arrPm: ArrayList<PopupMenu>) {
        val popupMenu = PopupMenu(this, btn)
        arrPm.add(popupMenu)
        popupMenu.inflate(R.menu.choose)

        if (btn.tag.toString().toInt() != 0)
            btn.setOnClickListener {
                popupMenu.show()
            }

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    main.playerChoose(btn.tag.toString().toInt())
                    blockPopups()
                    main.changeStateString("Give phone to next player")
                    isClicked = true
                    true
                }
                else -> false
            }
        }
    }

    private fun blockPopups() {
        for (btn in arrayBtn)
            btn.setOnClickListener(null)
    }
}

