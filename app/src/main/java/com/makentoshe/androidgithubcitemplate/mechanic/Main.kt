package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView

class Main(var hist: TextView, private val arrayBtn: Array<Button>,
           private val arrayPm: ArrayList<PopupMenu>,
           private val icon: TextView, private val state: TextView) {
    private val views = Views(arrayBtn, arrayPm, icon, state)
    private val history = History(hist)
    private val pm = PlayersManager(views, history)
    private val sm = StateManager(pm, history)

    fun playerChoose(id: Int) {
        pm.playerChoose(id)
    }

    fun startStep() {
        if (sm.getState() == "Day") pm.startStepDay()
        else pm.startStepNight()
    }
}