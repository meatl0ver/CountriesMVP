package raul.imashev.ui.countryDetail

import raul.imashev.model.Country
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CountryDetailView : MvpView {
    fun init()
    fun showCountryInfo(country: Country)

}