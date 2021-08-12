package com.demo.covidtracker.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.demo.covidtracker.databinding.ItemCountryBinding
import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity

class CountriesAdapter(private val listener: CountryItemListener) :
    RecyclerView.Adapter<CountryViewHolder>() {

    interface CountryItemListener {
        fun onClickedCountry(countryId: Int)
    }

    val items = ArrayList<CountryEntity>()

    fun setItems(items: ArrayList<CountryEntity>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding: ItemCountryBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(items[position])
}

class CountryViewHolder(
    private val itemBinding: ItemCountryBinding,
    private val listener: CountriesAdapter.CountryItemListener
) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var country: CountryEntity

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: CountryEntity) {
        this.country = item
        itemBinding.countryName.text = item.country
        itemBinding.totalCases.text = item.cases.toString()
        Glide.with(itemBinding.root)
            .load(item.countryInfo.flag)
            .transform(CircleCrop())
            .into(itemBinding.countryFlag)
    }

    override fun onClick(v: View?) {
        listener.onClickedCountry(country.id)
    }
}

