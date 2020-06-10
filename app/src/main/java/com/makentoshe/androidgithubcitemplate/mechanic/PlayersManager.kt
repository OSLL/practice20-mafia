package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager(var views: Views, var hist: History, nameArray: Array<String>) {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(0, 1, 2, 3, 4, 5)
    private var curAlive = 0
    private var mafiaChoose = (-1).toInt()
    private var doctorChoose = (-1).toInt()
    private var votingResults = Array<Int>(6) {0}
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
                }, i, views, nameArray[i]
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

        if (phase == "Day") {
            views.changeStateText(player.getText("Day"))
            player.dayAction(alives[(curAlive + alives.size - 1) % alives.size])
            for (i in alives.indices)
                if (i != curAlive)
                    views.restorePopup(alives[i])
        } else {
            views.changeStateText(playersList[alives[curAlive]].getText("Night"))
            player.nightAction(alives[(curAlive + alives.size - 1) % alives.size])
            for (i in alives.indices)
                if (i != curAlive)
                    views.restorePopup(alives[i])
                else if (playersList[alives[i]].isSelfChoose())
                    views.restorePopup(alives[i])
        }
        return true
    }

    fun changePhaseUpdateBackground(time: String) {
        val pFirst = playersList[alives[0]]

        if (lastId in alives) pFirst.dayAction(lastId)
        else pFirst.activeBtnAction()

        views.changeStateText(pFirst.getText(time))
        views.setIcon(if (time == "Day") "sun" else "moon")
        if (time == "Day")
            for (alive in alives)
                views.cntInit(alive)
        else views.cntsDel()


        for (i in alives.indices)
            if (i != curAlive)
                views.restorePopup(alives[i])
    }

    fun playerChooseDay(id: Int) {
        votingResults[id]++
        hist.write("${playersList[alives[curAlive]].getName()} voted for ${playersList[id].getName()}")
        views.cntIncrement(id)
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

    fun getPlayer(id: Int) : Player {
        return playersList[id]
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

