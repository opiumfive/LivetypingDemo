package com.opiumfive.livetypingdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.opiumfive.livetypingdemo.data.Meal
import kotlinx.android.synthetic.main.fragment_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class CatalogFragment : Fragment() {

    val viewModel: MealsViewModel by viewModel()

    val catalogObs: Observer<List<Meal>> by lazy { Observer<List<Meal>> { addProducts(it) } }

    val adapter = MealsAdapter()

    private fun initUI() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter
        recycler.addItemDecoration(RecyclerLineDecorator(requireContext()))
        recycler.addOnScrollListener(RecyclerScrollListener {
            viewModel.getNextCategory()
        })
    }

    private fun addProducts(list: List<Meal>?) {
        adapter.addList(list ?: emptyList())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        viewModel.catalogData.observe(this, catalogObs)

        viewModel.getCats()
    }

    override fun onDestroyView() {
        viewModel.catalogData.removeObserver(catalogObs)

        super.onDestroyView()
    }
}