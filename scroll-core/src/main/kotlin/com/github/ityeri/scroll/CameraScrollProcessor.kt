package com.github.ityeri.scroll

import com.badlogic.gdx.InputAdapter
import com.github.ityeri.scroll.ScrollApplicationAdapter

class CameraScrollProcessor(val adapter: ScrollApplicationAdapter) : InputAdapter() {
    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        adapter.goalZoom *= 1 + (amountY * adapter.wheelSensitivity)
        return true
    }
}
