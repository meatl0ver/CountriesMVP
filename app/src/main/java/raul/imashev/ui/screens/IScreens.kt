package raul.imashev.ui.screens

import raul.imashev.model.Country
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun countriesList(): Screen
    fun countryDetail(country: Country): Screen
}