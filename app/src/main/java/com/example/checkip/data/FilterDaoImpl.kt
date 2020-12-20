package com.example.checkip.data

import android.content.SharedPreferences
import com.example.checkip.Filter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class FilterDaoImpl(private val sharedPreferences: SharedPreferences) : FilterDao {

    private var filter: Filter
        get() = sharedPreferences.getString(FilterDaoImpl.FILTER_DAO_KEY, null)?.let {
            try {
                Json.decodeFromString(it)
            } catch (t: Throwable) {
                Filter()
            }
        } ?: Filter()
        set(value) {
            sharedPreferences.edit().putString(
                FilterDaoImpl.FILTER_DAO_KEY,
                Json.encodeToString(value)
            ).apply()
        }

    override fun isSetSearchField(): Boolean = (filter.search != "")

    override fun getSearchString(): String = filter.search

    override fun setSearchString(search: String) {
        filter = filter.copy(search = search)
    }

    override fun resetFilter() {
        filter = Filter()
    }

    companion object {
        private const val FILTER_DAO_KEY = "FILTER_DAO_KEY"
    }
}