# scroll

[![Java](https://img.shields.io/badge/Java-17-ED8B00.svg?logo=openjdk)](https://www.azul.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-585DEF.svg?logo=kotlin)](http://kotlinlang.org)
[![JitPack](https://jitpack.io/v/ityeri/scroll.svg)](https://jitpack.io/#ityeri/scroll)

---

libGDX 라이브러리에서 화면에 도형을 그리고 마우스로 자유롭게 
스크롤 할 수 있도록 구현하면 편할것 같았습니다

꽤 편하긴 합니다

# useShapeRenderer

하나의 렌더링 싸이클 내에서 여러개의 ShapeType 을 사용하여 도형을 그릴떈, 
보통 아래와 같은 패턴을 사용하게 됩니다

```kotlin
shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
circle(0f, 0f, 5f)
shapeRenderer.end()

shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
circle(10f, -2f, 10f)
shapeRenderer.end()
```

단순하지만 `begin` 과 `end` 가 붙어 다소 거추장스러워 보일수 있고,
`ShapeRenderer` 관련 런타임 에러가 발생할수 있습니다

`ScrollApplicationAdapter` 클래스에선 `useShapeRenderer` 라는 매서드를 제공하여
`begin` 과 `end` 와 `ShapeType` 을 깔끔히 제어해 줍니다

```kotlin
override fun shapeDraw() {
    useShapeRenderer(ShapeRenderer.ShapeType.Filled) {
        circle(0f, 0f, 5f)
    }
    useShapeRenderer(ShapeRenderer.ShapeType.Line) {
        circle(30f, 30f, 10f)
    }
}
```

> 권장되는 방식은 아니지만, 
`useShapeRenderer` 블럭 내부에서 `useShapeRenderer` 를 호출하는것도 가능합니다

# Gradle

scroll 은 jitpack 으로 배포됩니다

---

`build.gradle` (root)

```groovy
subprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}

```

---

`core/build.gradle`

```groovy
dependencies {
    api("com.github.ityeri:scroll:<version>")
}
```
