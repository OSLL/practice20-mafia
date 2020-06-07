package com.makentoshe.androidgithubcitemplate.mechanic

class Player(val role: Roles, private val id: Int, private val views: Views) {
    fun nightAction(): Int {
        return 0
    }

    fun getRole() : String {
        return role.role
    }

    fun dayAction(): Int {
        views.setBtnActive(id)
        return 0
    }
}