package raul.imashev.ui.screens

import raul.imashev.model.Country
import raul.imashev.ui.countriesList.CountriesListFragment
import raul.imashev.ui.countryDetail.CountryDetailFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun countriesList(): Screen = FragmentScreen { CountriesListFragment.newInstance() }
    override fun countryDetail(country: Country): Screen =
        FragmentScreen { CountryDetailFragment.newInstance(country) }

}