package com.rhezarijaya.thefoodee.ui.home.fragment.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Food
import com.rhezarijaya.core.ui.OnItemClick
import com.rhezarijaya.core.ui.adapter.ItemFoodAdapter
import com.rhezarijaya.core.utils.Constants
import com.rhezarijaya.thefoodee.R
import com.rhezarijaya.thefoodee.databinding.FragmentSearchBinding
import com.rhezarijaya.thefoodee.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModels()

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val itemFoodAdapter = ItemFoodAdapter(
                object : OnItemClick<Food> {
                    override fun onClick(data: Food) {
                        val intent = Intent(requireContext(), DetailActivity::class.java)
                        intent.putExtra(Constants.INTENT_ITEM_FOOD_TO_DETAIL, data.idMeal)

                        startActivity(intent)
                    }
                }, object : OnItemClick<Food> {
                    override fun onClick(data: Food) {
                        if (data.isOnFavorite) {
                            viewModel.removeFavorite(data)
                        } else {
                            viewModel.addFavorite(data)
                        }
                    }
                }
            )

            binding.apply {
                fragmentSearchRv.apply {
                    adapter = itemFoodAdapter
                    layoutManager = LinearLayoutManager(requireActivity())
                }

                fragmentSearchSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let {
                            if (it != "") {
                                itemFoodAdapter.submitList(listOf())
                                viewModel.setSearchQuery(it)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.search_blank_info),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean = false
                })
            }

            viewModel.getSearchData().observe(viewLifecycleOwner) { foodResource ->
                if (foodResource is Resource.Loading) {
                    binding.fragmentSearchLottieLoading.visibility = View.VISIBLE
                    binding.fragmentSearchLottieNoItem.visibility = View.GONE
                    binding.fragmentSearchTvNoItem.visibility = View.GONE
                } else {
                    binding.fragmentSearchLottieLoading.visibility = View.GONE
                }

                if (foodResource is Resource.Success) {
                    binding.fragmentSearchLottieNoItem.visibility =
                        if (foodResource.data?.isEmpty() == false) View.GONE else View.VISIBLE
                    binding.fragmentSearchTvNoItem.visibility =
                        if (foodResource.data?.isEmpty() == false) View.GONE else View.VISIBLE

                    itemFoodAdapter.submitList(foodResource.data)
                }

                if (foodResource is Resource.Error) {
                    binding.fragmentSearchLottieNoItem.visibility = View.VISIBLE
                    binding.fragmentSearchTvNoItem.visibility = View.VISIBLE
                    Toast.makeText(
                        requireContext(),
                        String.format(getString(R.string.error_search), foodResource.message),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}