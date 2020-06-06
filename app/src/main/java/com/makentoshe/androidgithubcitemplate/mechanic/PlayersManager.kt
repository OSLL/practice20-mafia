package com.makentoshe.androidgithubcitemplate.mechanic

class PlayersManager() {
    lateinit var playersList: Array<Player>
    private var alives = arrayListOf<Int>(1, 2, 3, 4, 5, 6)

    init {
        val mafiaChoose = (1..6).random()
        var doctorChoose = (1..6).random()
        while (doctorChoose == mafiaChoose)
            doctorChoose = (1..6).random()

        playersList = Array<Player>(6) { i -> Player(Simple(), i + 1) }
        playersList[mafiaChoose] = Player(Mafia(), mafiaChoose)
        playersList[doctorChoose] = Player(Doctor(), doctorChoose)
    }

    fun eraseId(id: Int) {
        alives.remove(id)
    }

    fun getAlives() : ArrayList<Int> {
        return alives
    }

    fun getNightEvents(): Pair<Int, Int> {
        return Pair(0, 0)
    }

    fun getVotingResults(): Array<Int> {
        return arrayOf(1, 2, 3)
    }
}

