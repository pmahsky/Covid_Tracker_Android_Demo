package com.demo.covidtracker.ui.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.covidtracker.databinding.CountriesFragmentBinding
import com.demo.covidtracker.domain.entities.dbEntities.CountryEntity
import com.demo.covidtracker.ui.adapters.CountriesAdapter
import com.demo.covidtracker.ui.viewmodels.CountriesViewModel
import com.demo.covidtracker.utils.Resource
import com.demo.covidtracker.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : Fragment(), CountriesAdapter.CountryItemListener {

    private var binding: CountriesFragmentBinding by autoCleared()
    private val viewModel: CountriesViewModel by viewModels()
    private lateinit var adapter: CountriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CountriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initSearchView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = CountriesAdapter(this)
        binding.countriesRv.layoutManager = LinearLayoutManager(requireContext())
        binding.countriesRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })

        viewModel.countriesListLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.setItems(it as ArrayList<CountryEntity>)
        })
    }

    private fun initSearchView() {
        activity?.let {
            val searchManager = it.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            binding.searchView.apply {
                setSearchableInfo(searchManager.getSearchableInfo(it.componentName))
                isSubmitButtonEnabled = false
                inputType = InputType.TYPE_CLASS_TEXT
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        viewModel.search(query.trim())
                        clearFocus()
                        return false
                    }

                    override fun onQueryTextChange(query: String): Boolean {
                        filterCountryList(query)
                        return false
                    }
                })
            }
        }

    }

    private fun filterCountryList(queryText: String) {
        viewModel.search(queryText)
        adapter.items.filter { it.country.contains(Regex(queryText)) }
        adapter.notifyDataSetChanged()
    }

    override fun onClickedCountry(countryId: Int) {
//        findNavController().navigate(
//            R.id.action_charactersFragment_to_characterDetailFragment,
//            bundleOf("id" to countryId)
//        )
    }
}
