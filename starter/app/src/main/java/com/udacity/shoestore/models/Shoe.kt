package com.udacity.shoestore.models

class Shoe(name: String ,size : String, company : String, description : String)
{
    var name : String = ""
    var size : String = ""
    var company : String = ""
    var description : String = ""

    init {
        this.name = name
        this.size = size
        this.company = company
        this.description = description
    }
}