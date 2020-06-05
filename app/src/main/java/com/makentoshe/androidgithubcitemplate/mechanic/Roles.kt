package com.makentoshe.androidgithubcitemplate.mechanic

abstract class Roles {
    abstract val text: String
    abstract val role: String

    fun choose(): Int {
        return 0
    }
}

class Simple() : Roles() {
    override val text: String
        get() = "Sleep"
    override val role: String
        get() = "Simple"
}

class Doctor() : Roles() {
    override val text: String
        get() = "Choose sbd to heal"
    override val role: String
        get() = "Doctor"
}

class Mafia() : Roles() {
    override val text: String
        get() = "Choose sbd to kill"
    override val role: String
        get() = "Mafia"
}
