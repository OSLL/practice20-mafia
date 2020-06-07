package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import com.makentoshe.androidgithubcitemplate.R

class Views(private val arrayBtn: Array<Button>, private val arrayPm: ArrayList<PopupMenu>,
            private val icon: TextView, private val state: TextView) {
    fun setBtnActive(id : Int)
    {
        arrayBtn[(id + 5) % 6].setBackgroundResource(R.drawable.circle_button)
        arrayBtn[id].setBackgroundResource(R.drawable.circle_button_active)
    }

    fun deletePopups() {

    }

    fun setPopupInactive() {

    }

    fun allPopups() {

    }

    fun changeStateText (text: String) {
        state.text = text
    }
}