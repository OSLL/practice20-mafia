package com.makentoshe.androidgithubcitemplate.mechanic

class Player(val role: Roles, private val id: Int, val views: Views, private val name: String) {
    fun nightAction(prevId: Int) {
        views.setBtnActive(id, prevId)
    }

    fun getRole() : String {
        return role.role
    }

    fun getRoleText() : String {
        return role.text
    }


    fun getText(time: String) : String = if (time == "Day") role.voteText else role.text

    fun dayAction(prevId: Int) {
        views.setBtnActive(id, prevId)
    }

    fun activeBtnAction() {
        views.setBtnActive(id)
    }
}