package com.github.ityeri

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.ityeri.scroll.ScrollApplicationAdapter

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : ScrollApplicationAdapter() {
    override fun shapeDraw() {
        shape(ShapeRenderer.ShapeType.Filled) {
            circle(0f, 0f, 10f)
        }
        shape(ShapeRenderer.ShapeType.Line) {
            circle(30f, 30f, 5f)
        }

    }

    override fun spriteDraw() {
    }

}
