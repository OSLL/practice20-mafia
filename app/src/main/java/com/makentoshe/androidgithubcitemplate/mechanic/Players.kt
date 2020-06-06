package com.makentoshe.androidgithubcitemplate.mechanic

open class Player {
    lateinit var role: Roles

    open fun nightAction() {

    }

    open fun dayAction() {

    }
}

class BotPlayer(): Player() {
    override fun nightAction() {

    }

    override fun dayAction() {

    }
}