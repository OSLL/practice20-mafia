package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.TextView

class StateManager(private val pm: PlayersManager, private var hist: History) {
    private var state: State = StateDay()
    var timesOfDay = true

    private fun changeGameState(newState: State) {
        state = newState
    }

    fun phase(): Boolean {
        val whatNext = pm.isEnd()

        if (whatNext == 0) {
            if (timesOfDay) changeGameState(StateDay())
            else changeGameState(StateNight())
            timesOfDay = !timesOfDay
            state.process(pm, hist)
            return true
        }

        if (whatNext == 1) hist.write("Mafia wins")
        else hist.write("Peaces wins")
        return false
    }

    fun getState(): String = state.text
}

abstract class State {
    abstract val text: String
    abstract fun process(PM: PlayersManager, hist: History)
}

class StateDay() : State() {
    override val text: String
        get() = "Day"
    override fun process(PM: PlayersManager, hist: History) {
        val votingResults = PM.getVotingResults()
        var playerToErase = 0
        var curCounter = -1

        hist.write("City is wake up")

        for (i in votingResults.indices) { //Если кол-во голосов одинаковое, то убиваем того, кто дальше от игрока
            if (votingResults[i] >= curCounter) {
                curCounter = votingResults[i]
                playerToErase = i
            }
        }

        hist.write("Player${playerToErase + 1} die today")
        PM.eraseId(playerToErase)
    }
}

class StateNight(): State() {
    override val text: String
        get() = "Night"
    override fun process(PM: PlayersManager, hist: History) {
        val nightChooses = PM.getNightEvents()
        val mafiaChoose = nightChooses[0]
        val doctorChoose = nightChooses[1]

        hist.write("City is falling asleep")

        if (mafiaChoose == doctorChoose) {
            hist.write("Nobody died")
        } else {
            hist.write("Player${mafiaChoose + 1} died")
            PM.eraseId(mafiaChoose)
        }
    }
}