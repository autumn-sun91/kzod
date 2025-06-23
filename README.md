# kzod

Zod-inspired, pure Kotlin runtime JSON schema validation library.

---

## 🔍 Overview

Zod-inspired pure Kotlin Runtime JSON schema validation

Kotlin 진영에서 런타임에 JSON 구조를 DSL 로 정의하고 유효성을 검증할 수 있는 오픈소스입니다.

Kzod 는 동적인 JSON 구조, 중첩객체 검증, data class 검증, JSON 파싱, diff 등 복잡한 형태의 JSON 을 처리하는 데에 특화되어잇습니다.

---

## 📦 Installation

```kotlin
// build.gradle.kts
dependencies {
    implementation("kzod:kzod:0.1.0")
}
```

## 🚀 Usage

```kotlin

val userSchema = kzodObj {
  "name" `is` string().min(10)
  "age" `is` int()
}

val result = userSchema.parse(mapOtf("name" to "Autumn", "age" to 20))

println(result.get())
// {"name": "Autumn", "age": 20}

```

---

📜 Licence
MIT License
