package com.rhezarijaya.thefoodee.ui.home.fragment.categorylist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.domain.model.Category
import com.rhezarijaya.core.ui.OnItemClick
import com.rhezarijaya.core.ui.adapter.ItemCategoryAdapter
import com.rhezarijaya.core.utils.Constants
import com.rhezarijaya.thefoodee.R
import com.rhezarijaya.thefoodee.databinding.FragmentCategoryListBinding
import com.rhezarijaya.thefoodee.ui.category.CategoryActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment : Fragment() {

    private val viewModel: CategoryListViewModel by viewModels()

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val itemCategoryAdapter =
                ItemCategoryAdapter(onItemClick = object : OnItemClick<Category> {
                    override fun onClick(data: Category) {
                        val intent = Intent(requireContext(), CategoryActivity::class.java)
                        intent.putExtra(Constants.INTENT_CATEGORY_LIST_TO_CATEGORY, data)

                        startActivity(intent)
                    }
                })

            binding.fragmentCategoryListRv.apply {
                adapter = itemCategoryAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }

            viewModel.getCategories().observe(viewLifecycleOwner) { categoriesResource ->
                binding.fragmentCategoryListProgressBar.visibility =
                    if (categoriesResource is Resource.Loading) View.VISIBLE else View.GONE

                if (categoriesResource is Resource.Success) {
                    itemCategoryAdapter.submitList(categoriesResource.data)
                }

                if (categoriesResource is Resource.Error) {
                    Toast.makeText(
                        requireContext(),
                        String.format(
                            getString(R.string.error_categories_list),
                            categoriesResource.message
                        ),
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