## ExpandableTextView (Android Kotlin Library)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9-blue?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green)](LICENSE)
[![API](https://img.shields.io/badge/API-24%2B-orange)](#)
---

A stable, lightweight, and RecyclerView-safe ExpandableTextView for Android that supports Read More / Read Less functionality without layout glitches.

---

### Features

- Expand / collapse long text
- Read More / Read Less action
- No animation glitches
- RecyclerView-safe
- Orientation-change safe (logic-wise)
- Fully customizable via XML
- Supports standard TextView attributes

---

---

## Installation (JitPack)

### 1️⃣ Add JitPack to your **root `settings.gradle` or `build.gradle`**

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
### Add Dependency
```
dependencies {
    implementation("com.github.Excelsior-Technologies-Community:Android_ExpandableTextView:1.0.1")
}
```

---

### Basic Usage

XML
```xml
<com.ext.expandabletextview.ExpandableTextView
    android:id="@+id/expandableText"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="This is a very long text that will initially be collapsed. Clicking Read More will expand it."
    app:collapsedLines="2"
    app:readMoreText="Read More"
    app:readLessText="Read Less"/>
```

Programmatic Usage (Kotlin)
```kotlin
expandableText.setText(
    "This is a very long text that will be collapsed initially."
)
//Expand / Collapse Manually
expandableText.expand()
expandableText.collapse()
expandableText.toggle()
//Check State
val expanded = expandableText.isExpanded()
```

**Listener Callbacks**
Listen for expand / collapse events:
```kotlin
expandableText.setExpandableTextListener(
    object : ExpandableTextView.ExpandableTextListener {

        override fun onExpanded() {
            // Called when expanded
        }

        override fun onCollapsed() {
            // Called when collapsed
        }

        override fun onToggle(isExpanded: Boolean) {
            // Called on any toggle
        }
    }
)
```

---

### License

```
MIT License

Copyright (c) 2025 Excelsior Technologies 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
