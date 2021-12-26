package raul.imashev.ui.countriesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import raul.imashev.databinding.FragmentCountriesListBinding
import raul.imashev.domain.CountriesRepo
import raul.imashev.utils.imageUtils.GlideImageLoader
import raul.imashev.remote.ApiHolder
import raul.imashev.room.database.Database
import raul.imashev.ui.base.BackButtonListener
import raul.imashev.ui.countriesList.adapter.CountriesRVAdapter
import raul.imashev.ui.screens.AndroidScreens
import raul.imashev.utils.networkStatus.AndroidNetworkStatus
import raul.imashev.utils.textUtils.TextUtil
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import raul.imashev.App

class CountriesListFragment : MvpAppCompatFragment(), CountriesListView, BackButtonListener {
    companion object {
        fun newInstance() = CountriesListFragment()
    }

    private val presenter: CountriesListPresenter by moxyPresenter {
        CountriesListPresenter(
            CountriesRepo(
                ApiHolder.api,
                AndroidNetworkStatus(requireContext()),
                Database.getInstance(),
                TextUtil()
            ),
            App.instance.router,
            AndroidScreens()
        )
    }
    private var vb: FragmentCountriesListBinding? = null
    private var adapter: CountriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentCountriesListBinding.inflate(inflater, container, false).also {
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.rvCountries?.layoutManager = GridLayoutManager(context, 2)//LinearLayoutManager(context)
        adapter = CountriesRVAdapter(presenter.countriesListPresenter, GlideImageLoader())
        vb?.rvCountries?.adapter = adapter
    }

    override fun updateList() {
        Log.d("ss", "ss")
    }

    override fun showCountries() {
        vb?.progressBar?.isVisible = false
        vb?.rvCountries?.isVisible = true
    }

    override fun showLoading() {
        vb?.progressBar?.isVisible = true
        vb?.rvCountries?.isVisible = false
    }

    override fun backPressed(): Boolean = presenter.backPressed()


}