package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager(var views: Views, var hist: History) {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(0, 1, 2, 3, 4, 5)
    private var curAlive = 0
    private var mafiaChoose = (-1e9).toInt()
    private var doctorChoose = (-1e9).toInt()
    private var votingResults = Array<Int>(6, {0})
    private var lastId = -1

    init {
        val mafiaChoose = (0..5).random()
        var doctorChoose = (0..5).random()
        while (doctorChoose == mafiaChoose)
            doctorChoose = (0..5).random()

        playersList = Array<Player>(6) { i ->
            Player(
                when (i) {
                    mafiaChoose -> Mafia()
                    doctorChoose -> Doctor()
                    else -> Citizen()
                }, i, views
            )
        }
        playersList[alives[0]].views.changeStateText(playersList[alives[0]].getText("Day"))
    }

    fun startStep(phase: String): Boolean {
        curAlive = (curAlive + 1) % alives.size
        var player = playersList[alives[curAlive]]

        if (curAlive == 0) {
            lastId = alives[alives.size - 1]
            return false
        }

        for (i in alives.indices)
            if (i != curAlive)
                views.restorePopup(i)

        if (phase == "Day") {
            views.changeStateText(player.getText("Day"))
            player.dayAction(alives[(curAlive + alives.size - 1) % alives.size])
        } else {
            views.changeStateText(playersList[alives[curAlive]].getText("Night"))
            player.nightAction(alives[(curAlive + alives.size - 1) % alives.size])
        }
        return true
    }

    fun changePhaseUpdateBackground(time: String) {
        val pFirst = playersList[alives[0]]
        if (lastId in alives) pFirst.dayAction(lastId)
        else pFirst.activeBtnAction()
        views.changeStateText(pFirst.getText(time))
        views.setIcon(if (time == "Day") "sun" else "moon")

        for (i in alives.indices)
            if (i != curAlive)
                views.restorePopup(i)
    }

    fun playerChooseDay(id: Int) {
        votingResults[id]++
    }

    fun playerChooseNight(id: Int) {
        val isMafia = playersList[alives[curAlive]].getRole() == "Mafia"
        val isDoctor = playersList[alives[curAlive]].getRole() == "Doctor"
        if (isMafia) mafiaChoose = id
        else if (isDoctor) doctorChoose = id
    }

    fun eraseId(id: Int) {
        views.setPlayerDie(id)
        alives.remove(id)
    }

    fun getAlives(): ArrayList<Int> {
        return alives
    }

    fun getNightEvents(): Array<Int> {
        val res = arrayOf(mafiaChoose, doctorChoose)
        mafiaChoose = -1
        doctorChoose = -1

        return res
    }

    fun getVotingResults(): Array<Int> {
        val resCopy = votingResults
        votingResults = arrayOf(0, 0, 0, 0, 0, 0)
        return resCopy
    }

    fun isEnd(): Int {
        var mafiaAlive = false
        var citizenAlive = false

        for (live in alives)
            if (playersList[live].role.role == "Mafia")
                mafiaAlive = true
            else
                citizenAlive = true

        when {
            mafiaAlive && citizenAlive -> return 0
            mafiaAlive && !citizenAlive -> return 1
            !mafiaAlive && citizenAlive -> return 2
        }

        return -1
    }
}

