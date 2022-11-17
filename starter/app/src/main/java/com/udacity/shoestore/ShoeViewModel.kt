package com.udacity.shoestore

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

// Since we are implementing data binding with live data i had to add
// these extra functions and variables. Previously, i had implemented
// the same functionality without binding to xml with way less code
// but the reviewer told me to add it cause it's required so i did.
// I hope it's readable.
class ShoeViewModel : ViewModel() {
    private var M_shoeList = MutableLiveData<ArrayList<Shoe>>()
    private var M_shoe = MutableLiveData<Shoe>()
    private var M_doneSaving = MutableLiveData<Boolean>()

    init {
        M_shoeList.value = ArrayList<Shoe>()
        M_shoe.value = Shoe("", "", "", "")
        M_doneSaving.value = false

    }

    fun getShoeList(): LiveData<ArrayList<Shoe>> {
        return M_shoeList
    }

    fun getShoe(): LiveData<Shoe> {
        return M_shoe
    }

    fun getDoneSaving(): LiveData<Boolean> {
        return M_doneSaving
    }

    fun updateShoeName(s: CharSequence?, start: Int, before: Int, count: Int) {
        M_shoe.value!!.name = s.toString()
    }

    fun updateShoeSize(s: CharSequence?, start: Int, before: Int, count: Int) {
        M_shoe.value!!.size = s.toString()
    }

    fun updateShoeCompany(s: CharSequence?, start: Int, before: Int, count: Int) {
        M_shoe.value!!.company = s.toString()
    }

    fun updateShoeDescription(s: CharSequence?, start: Int, before: Int, count: Int) {
        M_shoe.value!!.description = s.toString()
    }

    fun addShoeItem(view: View) {
        M_shoeList.value?.add(
            Shoe(
                M_shoe.value!!.name,
                M_shoe.value!!.size,
                M_shoe.value!!.company,
                M_shoe.value!!.description
            )
        )
        M_doneSaving.value = true
    }

    fun onNavigateDone() {
        M_doneSaving.value = false
    }

    fun emptyTheShoe() {
        M_shoe.value!!.name = ""
        M_shoe.value!!.size = ""
        M_shoe.value!!.company = ""
        M_shoe.value!!.description = ""
    }
}