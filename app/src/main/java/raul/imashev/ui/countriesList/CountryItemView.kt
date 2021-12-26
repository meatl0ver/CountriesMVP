package raul.imashev.ui.countriesList

import raul.imashev.ui.base.IItemView

interface CountryItemView : IItemView {
    fun setName(text: String)
    fun loadFlag(url: String)
}