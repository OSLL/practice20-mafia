package com.makentoshe.androidgithubcitemplate.mechanic

abstract class Role {
    abstract val text: String
    abstract val role: String

    fun choose(): Int {
        return 0;
    }
}

class Simple() : Role() {
    override val text: String
        get() = "Sleep"
    override val role: String
        get() = "Simple"
}

class Doctor() : Role() {
    override val text: String
        get() = "Choose sbd to heal"
    override val role: String
        get() = "Doctor"
}

class Mafia() : Role() {
    override val text: String
        get() = "Choose sbd to kill"
    override val role: String
        get() = "Mafia"
}
