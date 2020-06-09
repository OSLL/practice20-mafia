package com.makentoshe.androidgithubcitemplate.mechanic

class Player(val role: Roles, private val id: Int, val views: Views, private val name: String) {
    fun nightAction(prevId: Int) = views.setBtnActive(id, prevId)

    fun getRole() : String = role.role

    fun getRoleText() = role.text

    fun getName() = name

    fun getText(time: String): String = if (time == "Day") role.voteText else role.text

    fun dayAction(prevId: Int) = views.setBtnActive(id, prevId)

    fun activeBtnAction() = views.setBtnActive(id)
}