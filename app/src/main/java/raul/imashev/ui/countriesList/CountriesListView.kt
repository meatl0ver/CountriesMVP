package raul.imashev.ui.countriesList

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountriesListView : MvpView {
    fun init()
    fun updateList()
    fun showCountries()
    fun showLoading()
}