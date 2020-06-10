package com.makentoshe.androidgithubcitemplate.mechanic

interface Roles {
    val text : String
        get() = this.toString()
    val role : String
        get() = this.toString()
    val voteText: String
        get() = "Vote now"
    val selfChoose: Boolean
        get() = false

    fun choose(alives : ArrayList<Int>, myId : Int) : Int
}

class Citizen() : Roles {
    override val text: String
        get() = "Choose smb to wish good night"
    override val role: String
        get() = "Simple"

    override fun choose(alives : ArrayList<Int>, myId : Int): Int {
        return -1
    }
}

class Doctor() : Roles {
    override val text: String
        get() = "Choose smb to heal"
    override val role: String
        get() = "Doctor"
    override val selfChoose: Boolean
        get() = true

    override fun choose(alives : ArrayList<Int>, myId : Int): Int {
        val left = 0
        val right = alives.size - 1
        val roleChoose = (left..right).random()
        return alives[roleChoose]
    }
}

class Mafia() : Roles {
    override val text: String
        get() = "Choose smb to kill"
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
