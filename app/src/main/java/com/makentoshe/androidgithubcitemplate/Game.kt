package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.game.*

import com.makentoshe.androidgithubcitemplate.mechanic.Main

class Game : AppCompatActivity() {
    private lateinit var arrayBtn: Array<Button>
    private val arrayPm = ArrayList<PopupMenu>(6)
    private lateinit var main: Main
    private var isClicked = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        Log.d("game", "Game is here")

        history.movementMethod = ScrollingMovementMethod()
        createPopup(button0, arrayPm)
        createPopup(button1, arrayPm)
        createPopup(button2, arrayPm)
        createPopup(button3, arrayPm)
        createPopup(button4, arrayPm)
        createPopup(button5, arrayPm)

        start.setOnClickListener {
            if (!isClicked) {
               //Toast
                Toast.makeText(this, "Choose smb!", Toast.LENGTH_SHORT).show()
            } else {
                main.startStep()
                isClicked = false;
            }
        }

        arrayBtn = arrayOf(button0, button1, button2, button3, button4, button5)
        main = Main(history, arrayBtn, arrayPm, icon, state)
    }

    private fun createPopup(btn: Button, arrPm: ArrayList<PopupMenu>) {
        val popupMenu = PopupMenu(this, btn)
        arrPm.plus(popupMenu)
        popupMenu.inflate(R.menu.choose)

        btn.setOnClickListener {
            popupMenu.show()
        }

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    main.playerChoose(btn.tag.toString().toInt())
                    isClicked = true
                    true
                }
                else -> false
            }
        }
    }
}