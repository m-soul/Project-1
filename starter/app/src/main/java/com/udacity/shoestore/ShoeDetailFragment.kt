package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentShoeDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        viewModel = ViewModelProvider(activity!!).get(ShoeViewModel::class.java)
        viewModel.emptyTheShoe()
        binding.cancel.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }
        binding.shoeViewModel = viewModel
        binding.shoe = viewModel.getShoe().value
        binding.lifecycleOwner = this
        viewModel.getDoneSaving().observe(
            viewLifecycleOwner,
            Observer {
                if (it)
                {
                    this.findNavController()
                        .navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                    viewModel.onNavigateDone()
                }

            })
        return binding.root
    }

}