[![](https://jitpack.io/v/jpdev832/graphene.svg)](https://jitpack.io/#jpdev832/graphene)

# graphene

Graphene is a modular take on the classic RecyclerView adapter that takes advantage of DiffUtil. With Graphene, you no longer need to write individual adapters for each list of items you want to display in a Recyclerview. Graphene instead supports `CellModels`. These are an abstract representation of a view you want to display in a recyclerview. You can create any number of `CellModels` representations, and render them all using the same Graphene adapter.

# Setup

__Step 1.__ Add the JitPack repository to your build file
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```
__Step 2.__ Add the dependency

#### Core
The Core library contains the Graphene adapter, and a default DiffUtil adapter
```groovy
dependencies {
  implementation 'com.github.jpdev832.graphene:core:<latest-version>'
}
```

#### RxJava2
The RxJava2 library contains a RxJava2 implementation that will run the DiffUtil diff operation on a computation thread. This adapter produces a disposable for the diff work that can be cancelled manually or based on lifecycle.
```groovy
dependencies {
  implementation 'com.github.jpdev832.graphene:rxjava2:<latest-version>'
}
```

# Usage

Create an xml for the view you want to display in RecyclerView

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView xmlns:android="http://schemas.android.com/apk/res/android"
                                   xmlns:tools="http://schemas.android.com/tools"
                                   android:layout_width="match_parent"
                                   android:layout_height="72dp"
                                   android:clickable="true"
                                   android:focusable="true"
                                   android:foreground="?attr/selectableItemBackground">
    <TextView
            android:id="@+id/text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            tools:text="Hello, World!"/>

</androidx.cardview.widget.CardView>
```

Create a ViewHolder for your CellModel

```kotlin
class TextCellHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(text: String) {
        itemView.text.text = text
    }
}
```

Create a CellModel to represent the view you want to display in RecyclerView

```kotlin
class TextCellModel(var text: String) : CellModel<TextCellHolder>(R.layout.cell_text_card) {
    override fun createItemViewHolder(view: View): TextCellHolder = TextCellHolder(view)

    override fun bindItemViewHolder(holder: TextCellHolder, absPosition: Int) {
        holder.bind(text)
    }

    override fun getContentHashCode(): Int = text.hashCode()
}
```

Add the Graphene adapter to your activity or fragment and add CellModels to it

```kotlin
class ExampleActivity : AppCompatActivity() {
    companion object {
        val items: List<String> = listOf(
            "Cupcake",
            "KitKat",
            "Marshmallow",
            "Oreo"
        )
    }

    private val adapter = GrapheneAdapter(RxCallbackAdapter())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        setSupportActionBar(toolbar)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val cellModels = items.map { TextCellModel(it) }
        adapter.updateCells(cellModels)
    }
}
```

## License

```
MIT License

Copyright (c) 2019 Joel Parrish

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
