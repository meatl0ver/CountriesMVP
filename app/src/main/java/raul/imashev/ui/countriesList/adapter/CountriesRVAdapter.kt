package raul.imashev.ui.countriesList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import raul.imashev.databinding.CountryItemBinding
import raul.imashev.utils.imageUtils.IImageLoader
import raul.imashev.ui.base.ICountryListPresenter
import raul.imashev.ui.countriesList.CountryItemView

class CountriesRVAdapter(
    private val presenter: ICountryListPresenter,
    val imageLoader: IImageLoader<ImageView>
) :
    RecyclerView.Adapter<CountriesRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(

        CountryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).apply {
        itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
    }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: CountryItemBinding) : RecyclerView.ViewHolder(vb.root),
        CountryItemView {
        override fun setName(text: String) = with(vb) {
            textViewCountryName.text = text
        }

        override fun loadFlag(url: String) {
            imageLoader.loadInto(url, vb.imageViewFlag)
        }

        override var pos = -1
    }


}