package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.game.*


class Game : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        Log.d("game", "Game is here")
        var cnt = 0
        var sc = ScrollingMovementMethod()
        chat.movementMethod = sc
        btn.setOnClickListener{
            val message = chat.text.toString() + "Tuturu!" + cnt.toString() + "\n"
            chat.text = message
            cnt += 1
        }
        val popupMenu = PopupMenu(this, button1)
        popupMenu.inflate(R.menu.popupmenu)

        val popupMenu2 = PopupMenu(this, button2)
        popupMenu2.inflate(R.menu.popupmenu)

        val popupMenu3 = PopupMenu(this, button3)
        popupMenu3.inflate(R.menu.popupmenu)

        val popupMenu4 = PopupMenu(this, button4)
        popupMenu4.inflate(R.menu.popupmenu)

        val popupMenu5 = PopupMenu(this, button5)
        popupMenu5.inflate(R.menu.popupmenu)

        val popupMenu6 = PopupMenu(this, button6)
        popupMenu6.inflate(R.menu.popupmenu)

        button1.setOnClickListener{
            popupMenu.show()
        }

        button2.setOnClickListener{
            popupMenu2.show()
        }

        button3.setOnClickListener{
            popupMenu3.show()
        }

        button4.setOnClickListener{
            popupMenu4.show()
        }

        button5.setOnClickListener{
            popupMenu5.show()
        }

        button6.setOnClickListener{
            popupMenu6.show()
        }

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu1 -> {
                    text_view.text = "PopupMenu 1"
                    true
                }
                R.id.menu2 -> {
                    text_view.text = "PopupMenu 2"
                    true
                }
                else -> false
            }
        }
    }
}