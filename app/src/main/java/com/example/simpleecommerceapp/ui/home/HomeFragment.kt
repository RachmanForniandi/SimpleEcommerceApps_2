package com.example.simpleecommerceapp.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.simpleecommerceapp.R
import com.example.simpleecommerceapp.models.ListProductPromo.ResponseProductPromo
import com.example.simpleecommerceapp.ui.home.adapter.SliderAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
        viewModel.getProductPromo()

        attachObserve()
    }

    private fun attachObserve() {
        viewModel.responseProductPromo.observe(this, Observer { showImageSlider(it) })
    }

    private fun showImageSlider(it: ResponseProductPromo?) {
        imageSlider.sliderAdapter = SliderAdapter(it?.data)
    }

}
