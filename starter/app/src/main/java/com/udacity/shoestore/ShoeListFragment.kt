package com.udacity.shoestore

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding

class ShoeListFragment : Fragment() {
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.i("onCreate", "onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        viewModel = ViewModelProvider(activity!!).get(ShoeViewModel::class.java)
        viewModel.getShoeList().observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                for (i in it) {
                    val textView = TextView(this.context)
                    textView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    )
                    textView.textSize = 30f
                    textView.setPadding(0, 0, 0, 30)
                    textView.setTextColor(Color.BLACK)
                    textView.text =
                        "Name: ${i.name}\nsize: ${i.size}\nCompany: ${i.company}\nDescription: ${i.description}"
                    binding.myLinearLayout.addView(textView)

                }
            } else
            {
                val textView = TextView(this.context)
                textView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                )
                textView.textSize = 30f
                textView.setTextColor(Color.BLACK)
                textView.text =
                    "No items yet"
                binding.myLinearLayout.addView(textView)
            }


        })



        setHasOptionsMenu(true)
        binding.floatingActionButton.setOnClickListener {
            view!!.findNavController().navigate(R.id.action_shoeListFragment_to_shoeDetailFragment)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

}