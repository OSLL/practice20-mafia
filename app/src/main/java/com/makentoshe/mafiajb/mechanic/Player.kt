package com.makentoshe.mafiajb.mechanic

class Player(val role: Roles, private val id: Int, val views: Views, private val name: String) {
    fun nightAction(prevId: Int) = views.setBtnActive(id, prevId)

    fun getRole() : String = role.role

    fun getName() = name

    fun isSelfChoose() = role.isSelfChoose()

    fun getText(time: String): String = if (time == "Day") role.voteText else role.text

    fun dayAction(prevId: Int) = views.setBtnActive(id, prevId)

    fun activeBtnAction() = views.setBtnActive(id)
}