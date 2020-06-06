package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.TextView

class StateManager(private var tv: TextView) {
    private val hist = History(tv)
    private lateinit var state: State
    private val PM = PlayersManager()

    fun changeGameState(newState: State) {
        state = newState
    }

    fun phase() {
        
    }
}

abstract class State {
    abstract fun process(PM: PlayersManager, hist: History)
}

class StateDay() : State() {
    override fun process(PM: PlayersManager, hist: History) {
        val votingResults = PM.getVotingResults()
        var playerToErase = 0
        var curCounter = -1

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
    override fun process(PM: PlayersManager, hist: History) {
        val nightChooses = PM.getNightEvents()
        val mafiaChoose = nightChooses.first
        val doctorChoose = nightChooses.second

        if (mafiaChoose == doctorChoose) {
            hist.write("Nobody died")
        } else {
            hist.write("Player${mafiaChoose + 1} died")
            PM.eraseId(mafiaChoose)
        }
    }
}