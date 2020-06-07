package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager(views: Views, hist: History) {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(0, 1, 2, 3, 4, 5)
    private var curAlive = 0
    private var mafiaChoose = (-1e9).toInt()
    private var doctorChoose = (-1e9).toInt()
    private var votingResults: Array<Int>
    init {
        val mafiaChoose = (0..5).random()
        var doctorChoose = (0..5).random()
        while (doctorChoose == mafiaChoose)
            doctorChoose = (0..5).random()

        playersList = Array<Player>(6) { i -> Player(
            when(i) {
                mafiaChoose -> Mafia()
                doctorChoose -> Doctor()
                else -> Citizen()
            }, i, views) }
        votingResults = arrayOf(0, 0, 0, 0, 0, 0)
    }

    fun startStepDay(): Boolean {
        curAlive = (curAlive + 1) % alives.size
        /*if (curAlive == 0) {
            clearVoting()
            return false
        }*/



        playersList[alives[curAlive]].dayAction()
        return true
    }

    fun startStepNight(): Boolean {
        curAlive = (curAlive + 1) % alives.size

        if (curAlive == 0) {
            mafiaChoose = (-1e9).toInt()
            doctorChoose = (-1e9).toInt()
            return false
        }

        playersList[alives[curAlive]].nightAction()
        return true
    }

    fun playerChooseDay(id: Int) {
        votingResults[id] += 1
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

    fun getAlives() : ArrayList<Int> {
        return alives
    }

    fun getNightEvents(): Array<Int> {
        val res = arrayOf(mafiaChoose, doctorChoose)

        /*for (live in alives)
            if (playersList[live].role.role == "Mafia")
                mafiaChoose = playersList[live].nightAction(getAlives())
            else if (playersList[live].role.role == "Doctor")
                doctorChoose = playersList[live].nightAction(getAlives())*/

        return res
    }

    fun getVotingResults(): Array<Int> {
        /*for (live in alives)
            counter[playersList[live].dayAction(getAlives())]++*/
        return votingResults
    }

    fun isEnd(): Int {
        /*
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
        */
        return -1
    }

    fun clearVoting() {
        votingResults = arrayOf(0, 0, 0, 0, 0, 0)
    }
}

