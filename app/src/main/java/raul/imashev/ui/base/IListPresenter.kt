package raul.imashev.ui.base

import raul.imashev.ui.countriesList.CountryItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface ICountryListPresenter : IListPresenter<CountryItemView>
