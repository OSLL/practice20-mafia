package com.makentoshe.androidgithubcitemplate.mechanic

interface Roles {
    val text : String
        get() = this.toString()
    val role : String
        get() = this.toString()
    fun choose(alives : ArrayList<Int>, myId : Int) : Int
}

class Simple() : Roles {
    override val text: String
        get() = "Sleep"
    override val role: String
        get() = "Simple"

    override fun choose(alives : ArrayList<Int>, myId : Int): Int {
        return -1
    }
}

class Doctor() : Roles {
    override val text: String
        get() = "Choose sbd to heal"
    override val role: String
        get() = "Doctor"

    override fun choose(alives : ArrayList<Int>, myId : Int): Int {
        val left = 0
        val right = alives.size - 1
        val roleChoose = (left..right).random()
        return alives[roleChoose]
    }
}

class Mafia() : Roles {
    override val text: String
        get() = "Choose sbd to kill"
    override val role: String
        get() = "Mafia"
    override fun choose(alives : ArrayList<Int>, myId : Int): Int {
        alives.remove(myId)
        val left = 0
        val right = alives.size - 1
        val roleChoose = (left..right).random()
        return alives[roleChoose]
    }
}
