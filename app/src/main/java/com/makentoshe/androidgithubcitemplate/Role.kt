package com.makentoshe.androidgithubcitemplate

abstract class Role {
    abstract val text: String
    abstract val role: String
}

class Simple: Role() {
    override val text: String
        get() = "Sleep"
    override val role: String
        get() = "Simple"
}

class Doctor: Role() {
    override val text: String
        get() = "Choose who you want to heal"
    override val role: String
        get() = "Doctor"
}

class Mafia: Role() {
    override val text: String
        get() = "Choose who you want to kill"
    override val role: String
        get() = "Mafia"
}