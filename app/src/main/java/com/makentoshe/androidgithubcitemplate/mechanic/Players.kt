package com.makentoshe.androidgithubcitemplate.mechanic

open class Player(var role: Roles, var id: Int) {
    open fun nightAction(alives : ArrayList<Int>) : Int{
        return 0
    }

    open fun dayAction(alives : ArrayList<Int>) : Int{
        return 0
    }
}

class BotPlayer(role: Roles, id: Int): Player(role, id) {
    override fun nightAction(alives : ArrayList<Int>) : Int {
        return role.choose(alives, id)
    }

    override fun dayAction(alives : ArrayList<Int>) : Int {
        alives.remove(id)
        val left = 0
        val right = alives.size - 1
        val myChoose = (left..right).random()
        return alives[myChoose]
    }
}