# scroll

[![Java](https://img.shields.io/badge/Java-17-ED8B00.svg?logo=openjdk)](https://www.azul.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-585DEF.svg?logo=kotlin)](http://kotlinlang.org)
[![JitPack](https://jitpack.io/v/ityeri/scroll.svg)](https://jitpack.io/#ityeri/scroll)

---

libGDX 라이브러리에서 화면에 도형을 그리고 마우스로 자유롭게 
스크롤 할 수 있도록 구현하면 편할것 같았습니다

꽤 편하긴 합니다

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
    implementation("com.github.ityeri:scroll:<version>")
}
```
