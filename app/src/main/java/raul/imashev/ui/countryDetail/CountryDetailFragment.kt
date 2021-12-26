package raul.imashev.ui.countryDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import raul.imashev.utils.imageUtils.GlideImageLoader
import raul.imashev.utils.imageUtils.IImageLoader
import raul.imashev.model.Country
import raul.imashev.ui.base.BackButtonListener
import raul.imashev.utils.textUtils.ITextUtil
import raul.imashev.utils.textUtils.TextUtil
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import raul.imashev.App
import raul.imashev.databinding.FragmentCountryDetailBinding

class CountryDetailFragment(
    private val imageLoader: IImageLoader<ImageView>,
    private val textUtil: ITextUtil
) : MvpAppCompatFragment(), CountryDetailView, BackButtonListener {

    private val presenter: CountryDetailPresenter by moxyPresenter {
        CountryDetailPresenter(
            App.instance.router
        )
    }

    companion object {
        fun newInstance(country: Country): CountryDetailFragment {
            val args = Bundle().apply { putParcelable("COUNTRY_DETAILS", country) }
            val fragment = CountryDetailFragment(GlideImageLoader(), TextUtil())

            fragment.arguments = args
            return fragment
        }
    }

    private var countryDetailFragmentBinding: FragmentCountryDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCountryDetailBinding.inflate(inflater, container, false).also {
        countryDetailFragmentBinding = it
    }.root


    override fun init() {
        presenter.country = arguments?.getParcelable("COUNTRY_DETAILS")
        presenter.country?.let { showCountryInfo(it) }
    }

    @SuppressLint("SetTextI18n")
    override fun showCountryInfo(country: Country) {
        countryDetailFragmentBinding?.imageViewFlagDetail?.let {
            country.flags?.png?.let { it1 ->
                imageLoader.loadInto(
                    it1,
                    it
                )
            }
        }
        countryDetailFragmentBinding?.textViewOfficialName?.text = country.name?.official
        countryDetailFragmentBinding?.textViewCapital?.text = country.capital?.let {
            textUtil.getItems(
                it
            )
        }
        countryDetailFragmentBinding?.textViewArea?.text = "${country.area} m2"
        countryDetailFragmentBinding?.textViewPopulation?.text = "${country.population} people"
        countryDetailFragmentBinding?.textViewRegion?.text = country.region
        countryDetailFragmentBinding?.textViewFifa?.text = country.fifa
        countryDetailFragmentBinding?.textViewContinents?.text =
            country.continents?.let { textUtil.getItems(it) }

    }

    override fun backPressed(): Boolean = presenter.backPressed()


}