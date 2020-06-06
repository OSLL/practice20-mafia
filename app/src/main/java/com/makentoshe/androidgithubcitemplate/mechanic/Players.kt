package com.makentoshe.androidgithubcitemplate.mechanic

open class Player(private var role: Roles, private var id: Int) {
    open fun nightAction() : Int{
        return 0
    }

    open fun dayAction() : Int{
        return 0
    }
}

class BotPlayer(role: Roles, id: Int): Player(role, id) {
    override fun nightAction() : Int {
        return 0
    }

    override fun dayAction() : Int {
        return 0
    }
}