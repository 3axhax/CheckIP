package com.example.checkip.data

interface FilterDao {

    fun isSetSearchField() : Boolean

    fun getSearchString() : String

    fun setSearchString(search: String)

    fun resetFilter()

}