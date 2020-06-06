package com.makentoshe.androidgithubcitemplate.mechanic

import android.widget.TextView

class StateManager(private var tv: TextView) {
    private val hist = History(tv)
    private lateinit var state: State

    fun changeGameState(newState: State) {
        state = newState
    }
}

abstract class State {

}

class StateDay(): State() {

}

class StateNight(): State() {

}