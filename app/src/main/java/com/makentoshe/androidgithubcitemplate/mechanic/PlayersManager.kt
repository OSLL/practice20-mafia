package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager() {
    private var playersList: Array<Player>
    private var alives = arrayListOf<Int>(1, 2, 3, 4, 5, 6)

    init {
        val mafiaChoose = (1..6).random()
        var doctorChoose = (1..6).random()
        while (doctorChoose == mafiaChoose)
            doctorChoose = (1..6).random()

        playersList = Array<Player>(6) { i -> BotPlayer(Simple(), i + 1) }
        if (mafiaChoose == 1)
            playersList[mafiaChoose] = Player(Mafia(), mafiaChoose)
        else
            playersList[mafiaChoose] = BotPlayer(Mafia(), mafiaChoose)
        if (doctorChoose == 1)
            playersList[doctorChoose] = Player(Doctor(), doctorChoose)
        else
            playersList[doctorChoose] = BotPlayer(Doctor(), doctorChoose)
    }

    fun eraseId(id: Int) {
        alives.remove(id)
    }

    fun getAlives() : ArrayList<Int> {
        return alives
    }

    fun getNightEvents(): Pair<Int, Int> {
        var doctorChoose = 0
        var mafiaChoose = 0

        for (live in alives)
            if (playersList[live].role.role == "Mafia")
                mafiaChoose = playersList[live].nightAction(getAlives())
            else if (playersList[live].role.role == "Doctor")
                doctorChoose = playersList[live].nightAction(getAlives())

        return Pair(mafiaChoose, doctorChoose)
    }

    fun getVotingResults(): Array<Int> {
        return arrayOf(1, 2, 3)
    }
}

