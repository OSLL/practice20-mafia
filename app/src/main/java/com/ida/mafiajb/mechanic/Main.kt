package com.ida.mafiajb.mechanic

import android.widget.*

class Main(hist: TextView, arrayBtn: Array<Button>,
           arrayPm: ArrayList<PopupMenu>,
           icon: ImageView, state: TextView,
           exit: Button, start: Button,
           nameArray: Array<String>, playerCounter: Int,
           scrollView: ScrollView) {
    private val views = Views(
        arrayBtn,
        arrayPm,
        icon,
        state,
        exit,
        start
    )
    private val history =
        History(hist, scrollView)
    private val pm = PlayersManager(
        views,
        history,
        nameArray,
        playerCounter
    )
    private val sm = StateManager(pm, history)

    fun playerChoose(id: Int) {
        if (sm.getState() == "Day") pm.playerChooseDay(id)
        else pm.playerChooseNight(id)
    }

    fun changeStateString(text : String) {
        views.changeStateText(text)
    }

    fun startStep() {
        val res = if (sm.getState() == "Day") pm.startStep("Day") else pm.startStep("Night")
        val endChecker: Int

        if (!res) {
            sm.process()
            sm.changePhase()
            pm.changePhaseUpdateBackground(sm.getState())

            endChecker = pm.isEnd()
            if (endChecker == 1) {
                views.changeStateText("Mafia wins")
                views.showExit()
                views.blockPopups()
            } else if (endChecker == 2) {
                views.changeStateText("Citizen wins")
                views.showExit()
                views.blockPopups()
            }
        }
    }
}