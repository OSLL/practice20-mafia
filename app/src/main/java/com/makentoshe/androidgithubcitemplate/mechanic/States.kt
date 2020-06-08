package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.TextView

class StateManager(private val pm: PlayersManager, private var hist: History) {
    private var state: State = StateDay()

    fun changePhase() {
        if (this.getState() == "Day") this.state = StateNight()
        else this.state = StateDay()
        if (this.getState() == "Day") hist.write("City is wake up")
        else hist.write("City is falling asleep")
    }

    fun getState(): String = state.text
    fun process() = state.process(pm, hist)
}

abstract class State {
    abstract val text: String
    abstract fun process(pm: PlayersManager, hist: History)
}

class StateDay() : State() {
    override val text: String
        get() = "Day"

    override fun process(pm: PlayersManager, hist: History) {
        val votingResults = pm.getVotingResults()
        var playerToErase = 0
        var curCounter = -1
        var smbDie = false

        for (i in votingResults.indices) { //Если кол-во голосов одинаковое, то убиваем того, кто дальше от игрока
            if (votingResults[i] >= curCounter) {
                curCounter = votingResults[i]
                playerToErase = i
            }
            if (votingResults[i] > 0)
                smbDie = true
        }

        if (smbDie) {
            hist.write("Player${playerToErase + 1} die today")
            pm.eraseId(playerToErase)
        }
        else
            hist.write("Nobody die")
    }
}

class StateNight(): State() {
    override val text: String
        get() = "Night"

    override fun process(pm: PlayersManager, hist: History) {
        val nightChooses = pm.getNightEvents()
        val mafiaChoose = nightChooses[0]
        val doctorChoose = nightChooses[1]

        if (mafiaChoose == doctorChoose) {
            hist.write("Nobody died")
        } else {
            hist.write("Player${mafiaChoose + 1} died")
            pm.eraseId(mafiaChoose)
        }
    }
}