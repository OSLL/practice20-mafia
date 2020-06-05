package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.game.*


class Game : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        Log.d("game", "Game is here")

        history.movementMethod = ScrollingMovementMethod()

        createPopup(button1)
        createPopup(button2)
        createPopup(button3)
        createPopup(button4)
        createPopup(button5)
        createPopup(button6)
    }

    private fun createPopup(btn: Button) {
        val popupMenu = PopupMenu(this, btn)
        popupMenu.inflate(R.menu.popupmenu)

        btn.setOnClickListener{
            popupMenu.show()
        }

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    val message = history.text.toString() + "P1 " + btn.id + "\n"
                    history.text = message
                    true
                }
                R.id.menu2 -> {
                    val message = history.text.toString() + "P2 " + btn.id + "\n"
                    history.text = message
                    true
                }
                else -> false
            }
        }
    }
}