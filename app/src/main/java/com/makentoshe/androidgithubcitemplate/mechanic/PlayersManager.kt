package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager(views: Views, hist: History) {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(0, 1, 2, 3, 4, 5)
    private var curAlive = 0

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
    }

    fun startStepDay(): Boolean {
        curAlive = (curAlive + 1) % alives.size
        /*
        if (curAlive == 0)
            return false

         */

        playersList[alives[curAlive]].dayAction()
        return true
    }

    fun startStepNight() {

    }

    fun playerChoose(id: Int) {

    }

    fun eraseId(id: Int) {
        alives.remove(id)
    }

    fun getAlives() : ArrayList<Int> {
        return alives
    }

    fun getNightEvents(): Pair<Int, Int> {
        var doctorChoose = -1
        var mafiaChoose = -1

        /*for (live in alives)
            if (playersList[live].role.role == "Mafia")
                mafiaChoose = playersList[live].nightAction(getAlives())
            else if (playersList[live].role.role == "Doctor")
                doctorChoose = playersList[live].nightAction(getAlives())*/

        return Pair(mafiaChoose, doctorChoose)
    }

    fun getVotingResults(): Array<Int> {
        val counter = arrayOf(0, 0, 0, 0, 0, 0)

        /*for (live in alives)
            counter[playersList[live].dayAction(getAlives())]++*/
        return counter
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
}

