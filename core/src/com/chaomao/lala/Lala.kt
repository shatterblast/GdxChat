package com.chaomao.lala

import com.badlogic.gdx.Game

class Lala : Game() {
    override fun create() {
        setScreen(ChatScreen())
    }
}
