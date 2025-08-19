package com.github.ityeri.scroll

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport

abstract class ScrollApplicationAdapter : ApplicationAdapter() {
    lateinit var camera: OrthographicCamera
    lateinit var viewport: ExtendViewport
    lateinit var batch: SpriteBatch
    protected lateinit var shapeRenderer: ShapeRenderer

    var wheelSensitivity = 0.1f
    var zoomSoftness = 10f

    var lastTouchX = 0f
    var lastTouchY = 0f

    var goalZoom = 1f
    var isDragging = false

    val graphicWidth get() = Gdx.graphics.width
    val graphicHeight get() = Gdx.graphics.height

    override fun create() {

        camera = OrthographicCamera()
        camera.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        viewport = ExtendViewport(500f, 500f, camera)

        camera.position.set(0f, 0f, 0f)
        camera.update()

        batch = SpriteBatch()
        shapeRenderer = ShapeRenderer()

        Gdx.input.inputProcessor = CameraScrollProcessor(this)

    }

    override fun render() {
        val dt = Gdx.graphics.deltaTime

        input(dt)
        logic(dt)

        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        ScreenUtils.clear(0f, 0f, 0f, 0f)

        // sprite 작업
        batch.projectionMatrix = Matrix4()
            .setToOrtho2D(0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        batch.begin()

        spriteDraw()

        batch.end()

        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)

        // shape 작업
        shapeRenderer.projectionMatrix = camera.combined
        shapeDraw()
    }

    fun shape(
        shapeType: ShapeRenderer.ShapeType = ShapeRenderer.ShapeType.Filled,
        block: ShapeRenderer.() -> Unit) {
        shapeRenderer.begin(shapeType)
        shapeRenderer.apply(block)
        shapeRenderer.end()
    }

    open fun input(dt: Float) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

            // 처음 드래그를 시작하면
            if (!isDragging) {
                lastTouchX = Gdx.input.x.toFloat()
                lastTouchY = Gdx.input.y.toFloat()
                isDragging = true
            }
            // 드래그를 하는 도중이이면
            else {
                val currentScreen = Vector2(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
                val currentWorld: Vector2
                val lastWorld: Vector2

                synchronized(viewport) {
                    currentWorld = viewport.unproject(currentScreen.cpy())
                    lastWorld = viewport.unproject(Vector2(lastTouchX, lastTouchY))
                }


                val deltaX = lastWorld.x - currentWorld.x
                val deltaY = lastWorld.y - currentWorld.y

                camera.translate(deltaX, deltaY)

                lastTouchX = Gdx.input.x.toFloat()
                lastTouchY = Gdx.input.y.toFloat()
            }
        } else {
            isDragging = false
        }
    }

    open fun logic(dt: Float) {

        var zoomDt = zoomSoftness * dt
        if (1 < zoomDt) { zoomDt = 1f }

        camera.zoom += (goalZoom - camera.zoom) * zoomDt

        camera.update()

    }

    abstract fun spriteDraw()
    abstract fun shapeDraw()

    override fun dispose() {
        batch.dispose()
        shapeRenderer.dispose()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
    }
}
