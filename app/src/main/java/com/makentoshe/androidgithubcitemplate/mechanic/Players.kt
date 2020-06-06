package com.makentoshe.androidgithubcitemplate.mechanic

open class Player(private var role: Roles, private var id: Int) {
    open fun nightAction() {

    }

    open fun dayAction() {

    }
}

class BotPlayer(role: Roles, id: Int): Player(role, id) {
    override fun nightAction() {

    }

    override fun dayAction() {

    }
}