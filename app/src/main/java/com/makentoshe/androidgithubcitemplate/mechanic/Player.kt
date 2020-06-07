package com.makentoshe.androidgithubcitemplate.mechanic

class Player(val role: Roles, private val id: Int, val views: Views) {
    fun nightAction(prevId: Int) {
        views.setBtnActive(id, prevId)
    }

    fun getRole() : String {
        return role.role
    }

    fun getRoleText() : String {
        return role.text
    }

    fun getVoteText() : String {
        return role.voteText
    }

    fun dayAction(prevId: Int) {
        views.setBtnActive(id, prevId)
    }
}