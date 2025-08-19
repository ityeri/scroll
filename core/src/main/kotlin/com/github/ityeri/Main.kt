package com.github.ityeri

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.ityeri.scroll.ScrollApplicationAdapter

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : ScrollApplicationAdapter() {
    override fun shapeDraw() {
        useShapeRenderer(ShapeRenderer.ShapeType.Filled) {
            circle(0f, 0f, 5f)
        }
        useShapeRenderer(ShapeRenderer.ShapeType.Line) {
            circle(30f, 30f, 10f)
            useShapeRenderer(ShapeRenderer.ShapeType.Filled) {
                circle(45f, 15f, 2f)
            }
            circle(50f, 30f, 2f)
        }

    }

    override fun spriteDraw() {
    }

}
