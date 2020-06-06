package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager() {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(0, 1, 2, 3, 4, 5)

    init {
        val mafiaChoose = (0..5).random()
        var doctorChoose = (0..5).random()
        while (doctorChoose == mafiaChoose)
            doctorChoose = (0..5).random()

        playersList = Array<Player>(6) { i -> BotPlayer(Simple(), i) }
        if (mafiaChoose == 0)
            playersList[mafiaChoose] = Player(Mafia(), mafiaChoose)
        else
            playersList[mafiaChoose] = BotPlayer(Mafia(), mafiaChoose)
        if (doctorChoose == 0)
            playersList[doctorChoose] = Player(Doctor(), doctorChoose)
        else
            playersList[doctorChoose] = BotPlayer(Doctor(), doctorChoose)
        if (mafiaChoose != 0 && doctorChoose != 0)
            playersList[0] = Player(Simple(), 0)
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

        for (live in alives)
            if (playersList[live].role.role == "Mafia")
                mafiaChoose = playersList[live].nightAction(getAlives())
            else if (playersList[live].role.role == "Doctor")
                doctorChoose = playersList[live].nightAction(getAlives())

        return Pair(mafiaChoose, doctorChoose)
    }

    fun getVotingResults(): Array<Int> {
        var counter = arrayOf(0, 0, 0, 0, 0, 0)

        for (live in alives)
            counter[playersList[live].dayAction(getAlives())]++
        return counter
    }
}

