package com.github.ityeri

import com.badlogic.gdx.ApplicationAdapter
import com.github.ityeri.scroll.ScrollApplicationAdapter

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : ScrollApplicationAdapter() {
    override fun shapeDraw() {
        shapeRenderer.circle(0f, 0f, 10f)

        shapeRenderer.circle(30f, 30f, 5f)
    }

    override fun spriteDraw() {
    }

}
