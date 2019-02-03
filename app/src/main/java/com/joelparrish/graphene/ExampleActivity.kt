package com.joelparrish.graphene

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joelparrish.graphene.adapter.rxjava2.RxCallbackAdapter
import com.joelparrish.graphene.cell.DetailCellModel
import com.joelparrish.graphene.cell.TextCellModel
import com.joelparrish.graphene.core.CellModel
import com.joelparrish.graphene.core.GrapheneAdapter
import com.joelparrish.graphene.decorator.SpaceItemDecorator
import com.joelparrish.graphene.model.Detail
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.android.synthetic.main.content_example.*

class ExampleActivity : AppCompatActivity() {
    companion object {
        val items: List<String> = listOf(
            "Cupcake",
            "KitKat",
            "Marshmallow",
            "Oreo"
        )

        val details: List<Detail> = listOf(
            Detail("Honeycomb", "Android 3.0 - the first tablet-only Android update"),
            Detail("Lollipop", "Android 5.0 - Material Design"),
            Detail("Pie", "Android 9.0 - AI")
        )
    }

    private val adapter = GrapheneAdapter(RxCallbackAdapter())
    private var cellModels: List<CellModel<out RecyclerView.ViewHolder>> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { shuffle() }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(SpaceItemDecorator(bottom = resources.getDimensionPixelSize(R.dimen.decorator_space)))

        cellModels = items.map { TextCellModel(it) } + details.map { DetailCellModel(it, ::onDetailClick) }
        updateAdapter()
    }

    private fun updateAdapter() {
        adapter.updateCells(cellModels)
    }

    private fun onDetailClick(detailCellModel: DetailCellModel) {
        detailCellModel.showArea = !detailCellModel.showArea
        updateAdapter()
    }

    private fun shuffle() {
        cellModels = adapter.cellModels.shuffled()
        updateAdapter()
    }
}
