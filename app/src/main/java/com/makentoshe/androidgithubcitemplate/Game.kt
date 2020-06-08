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
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.game.*

import com.makentoshe.androidgithubcitemplate.mechanic.Main

class Game : AppCompatActivity() {
    lateinit var arrayBtn: Array<Button>
    val arrayPm = ArrayList<PopupMenu>(6)
    lateinit var main: Main
    var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        Log.d("game", "Game is here")

        history.movementMethod = ScrollingMovementMethod()
        arrayBtn = arrayOf(button0, button1, button2, button3, button4, button5)

        for (btn in arrayBtn)
            createPopup(btn, arrayPm)

        start.setOnClickListener {
            if (!isClicked) {
                Toast.makeText(this, "Choose smb!", Toast.LENGTH_SHORT).show()
            } else {
                main.startStep()
                isClicked = false;
            }
        }

        exit.setBackgroundResource(R.drawable.exit)
        exit.setOnClickListener {
            var myIntent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(myIntent)
        }

        arrayBtn = arrayOf(button0, button1, button2, button3, button4, button5)
        main = Main(history, arrayBtn, arrayPm, icon, state, exit, start)
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

