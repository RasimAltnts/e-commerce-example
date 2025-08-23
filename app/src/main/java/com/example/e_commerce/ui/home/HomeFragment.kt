package com.example.e_commerce.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.e_commerce.R
import com.example.e_commerce.components.ProductComponentUIModel
import com.example.e_commerce.databinding.FragmentHomeBinding
import com.example.e_commerce.ui.adapter.EqualSpacingItemDecoration
import com.example.e_commerce.ui.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var adapter: ProductAdapter

    /**
     * Kullanıcı favorite buttonuna tıkladığında bunu altında çalışır
     */
    private val onFavoriteClickListener: (ProductComponentUIModel) -> Unit = { uiModel->
        viewModel.updateFavoriteProduct(uiModel)
    }

    /**
     * Kullanıcı addToCard buttonuna bastığında bunun altında çalışır
     */
    private val addToCardListener: (ProductComponentUIModel) -> Unit = {

    }

    /**
     * Kullanıcı Item a tıkladıgında bunun altında çalışır
     */
    private val onItemClickListener: (ProductComponentUIModel) -> Unit = {
        println("onItemClicked::${it.isFavorite}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFlows()
        initAdapter()
        viewModel.fetchProducts()
    }

    private fun initAdapter() {
        adapter = ProductAdapter(
            products = emptyList(),
            onAddToCardListener = addToCardListener,
            onFavoriteClickListener = onFavoriteClickListener,
            onItemClickListener = onItemClickListener
        )
        binding.productRecycleView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productRecycleView.adapter = adapter

        /**
         * Bu kısımda itemlerin sağ ve sol dan paddingleri ayarlanıyor.
         */
        val spacing = resources.getDimensionPixelSize(R.dimen.padding_24) // dp -> px
        val itemDecoration = EqualSpacingItemDecoration(2, spacing,true)
        binding.productRecycleView.addItemDecoration(itemDecoration)
    }


    private fun initFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { products ->
                    products?.let {
                        adapter.submitList(it)
                    }
                }

                viewModel.saveProductStatus.collect { result ->
                    if (result) {
                        Toast.makeText(requireContext(),"Islem başarılı",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(requireContext(),"Islem başarısız",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}