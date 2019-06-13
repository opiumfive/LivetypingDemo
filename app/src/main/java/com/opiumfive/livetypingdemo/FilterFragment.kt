package com.opiumfive.livetypingdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.opiumfive.livetypingdemo.data.Category
import kotlinx.android.synthetic.main.fragment_filter.*
import org.koin.android.viewmodel.ext.android.viewModel

class FilterFragment : Fragment() {

    val viewModel: MealsViewModel by viewModel()

    val catsObs: Observer<List<Category>> by lazy { Observer<List<Category>> { setCategories(it) } }

    val adapter = FilterAdapter {

    }

    private fun initUI() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = adapter

        accept.setOnClickListener {
            findNavController().navigate(R.id.accept_action)
        }
    }

    private fun setCategories(list: List<Category>?) {
        adapter.addList(list ?: emptyList())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()

        viewModel.catsData.observe(this, catsObs)

        viewModel.getCats()
    }

    override fun onDestroyView() {
        viewModel.catsData.removeObserver(catsObs)

        super.onDestroyView()
    }
}