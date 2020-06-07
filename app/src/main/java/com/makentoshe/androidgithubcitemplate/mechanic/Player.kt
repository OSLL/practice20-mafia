package com.makentoshe.androidgithubcitemplate.mechanic

class Player(val role: Roles, private val id: Int, private val views: Views) {
    fun nightAction(prevId: Int) {
        views.setBtnActive(id, prevId)
    }

    fun getRole() : String {
        return role.role
    }

    fun dayAction(prevId: Int) {
        views.setBtnActive(id, prevId)
    }
}