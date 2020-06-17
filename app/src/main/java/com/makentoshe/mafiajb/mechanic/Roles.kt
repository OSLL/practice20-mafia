package com.makentoshe.mafiajb.mechanic

interface Roles {
    val text : String
        get() = this.toString()
    val role : String
        get() = this.toString()
    val voteText: String
        get() = "Vote now"

    fun isSelfChoose(): Boolean = false

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
    var selfHealCnt = 0

    override fun isSelfChoose(): Boolean {
        if (selfHealCnt == 0) {
            selfHealCnt++
            return true
        }
        return false
    }

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
