package com.makentoshe.androidgithubcitemplate.mechanic

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView

class Main(var hist: TextView, private val arrayBtn: Array<Button>,
           private val arrayPm: ArrayList<PopupMenu>,
           private val icon: ImageView, private val state: TextView, private val start : Button) {
    private val views = Views(arrayBtn, arrayPm, icon, state)
    private val history = History(hist)
    private val pm = PlayersManager(views, history)
    private val sm = StateManager(pm, history)

    fun playerChoose(id: Int) {
        if (sm.getState() == "Day") pm.playerChooseDay(id)
        else pm.playerChooseNight(id)
    }

    fun changeStateString(text : String) {
        views.changeStateText(text)
    }

    fun startStep(){
        val res = if (sm.getState() == "Day") pm.startStep("Day") else pm.startStep("Night")
        var endChecker: Int

        if (!res) {
            sm.process()
            sm.changePhase()
            pm.changePhaseUpdateBackground(sm.getState())

            endChecker = pm.isEnd()
            if (endChecker == 1) {
                history.write("Mafia wins")
                start.visibility = View.GONE
            } else if (endChecker == 2) {
                history.write("Citizen wins")
                start.visibility = View.GONE
            }
        }
    }
}