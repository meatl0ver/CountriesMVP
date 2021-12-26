package raul.imashev.ui.countryDetail

import raul.imashev.model.Country
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class CountryDetailPresenter(
    private val router: Router,
) : MvpPresenter<CountryDetailView>() {
    var country: Country? = null


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        country?.let { viewState.showCountryInfo(it) }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}