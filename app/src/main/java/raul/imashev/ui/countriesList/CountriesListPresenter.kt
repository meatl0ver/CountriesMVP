package raul.imashev.ui.countriesList

import android.util.Log
import raul.imashev.domain.CountriesRepo
import raul.imashev.model.Country
import raul.imashev.ui.base.ICountryListPresenter
import raul.imashev.ui.screens.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpPresenter

class CountriesListPresenter(
    private val countriesRepo: CountriesRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<CountriesListView>() {
    class CountriesListPresenter : ICountryListPresenter {
        val countries = mutableListOf<Country>()
        override var itemClickListener: ((CountryItemView) -> Unit)? = null

        override fun getCount() = countries.size

        override fun bindView(view: CountryItemView) {
            val country = countries[view.pos]
            country.name.let { it?.common?.let { name -> view.setName(name) } }
            country.flags?.png.let { it?.let { png -> view.loadFlag(png) } }
        }
    }

    val countriesListPresenter = CountriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        countriesListPresenter.itemClickListener = { itemView ->
            val country = countriesListPresenter.countries[itemView.pos]
            router.navigateTo(screens.countryDetail(country))
        }
    }

    private fun loadData() {
        countriesRepo.getCountries()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .subscribe({ countries ->
                countriesListPresenter.countries.addAll(countries)
                viewState.updateList()
                viewState.showCountries()
            }, {
                Log.e("Error", "Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}