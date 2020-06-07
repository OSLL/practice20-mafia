package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager(views: Views, hist: History) {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(0, 1, 2, 3, 4, 5)
    private var curAlive = 0
    private var mafiaChoose = (-1e9).toInt()
    private var doctorChoose = (-1e9).toInt()
    private var votingResults: Array<Int>
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
        votingResults = arrayOf(0, 0, 0, 0, 0, 0)
    }

    fun startStepDay(): Boolean {
        curAlive = (curAlive + 1) % alives.size

        if (curAlive == 0) {
            lastId = alives[alives.size - 1]
            return false
        }
        playersList[alives[curAlive]].views.changeStateText(playersList[alives[curAlive]].getVoteText())
        playersList[alives[curAlive]].dayAction(alives[(curAlive + alives.size - 1) % alives.size])
        return true
    }

    fun startStepNight(): Boolean {
        curAlive = (curAlive + 1) % alives.size

        if (curAlive == 0) {
            lastId = alives[alives.size - 1]
            return false
        }
        playersList[alives[curAlive]].views.changeStateText(playersList[alives[curAlive]].getRoleText())
        playersList[alives[curAlive]].nightAction(alives[(curAlive + alives.size - 1) % alives.size])
        return true
    }

    fun changePhaseUpdateBackground() {
        playersList[alives[0]].dayAction(lastId)
        playersList[alives[0]].views.changeStateText(playersList[alives[0]].getVoteText())
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

