package com.makentoshe.mafiajb.mechanic

import android.widget.*

class Main(var hist: TextView, private val arrayBtn: Array<Button>,
           private val arrayPm: ArrayList<PopupMenu>,
           private val icon: ImageView, private val state: TextView,
           private val exit: Button, private val start: Button,
           private val nameArray: Array<String>, private val playerCounter: Int,
           private val scrollView: ScrollView) {
    private val views = Views(
        arrayBtn,
        arrayPm,
        icon,
        state,
        exit,
        start,
        playerCounter
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
        var endChecker: Int

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