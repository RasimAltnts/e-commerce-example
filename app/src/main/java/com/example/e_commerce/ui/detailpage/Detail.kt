package com.example.e_commerce.ui.detailpage

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.e_commerce.databinding.FragmentDetailBinding
import com.example.e_commerce.R
import com.example.e_commerce.SharedViewModel
import com.example.e_commerce.components.ProductComponentUIModel
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Detail : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by viewModels()
    private val args by navArgs<DetailArgs>()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolBarName()
        initFlows()

        viewModel.setProduct(args.productModel)

        binding.addToCartButton.setOnClickListener {
            sharedViewModel.addToCard(args.productModel)
        }

        binding.favoriteIcon.setOnClickListener {
            viewModel.updateFavoriteState(args.productModel)
        }
    }

    /**
     * Bu fonksiyon toolbar isimi urun ismi ile degistirme islemi yapmaktadir.
     */
    private fun setToolBarName() {
        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.title = args.productModel.name
    }

    /**
     * Bu fonksiyon args tan gelen datalar ile ekrani doldurmaktadir
     */
    private fun fillData(model: ProductComponentUIModel) {
        binding.productTitle.text = model.name
        binding.productDesc.text = model.desc
        binding.favoriteIcon.isSelected = model.isFavorite
        binding.priceTextView.text = model.price + "₺"
    }

    private fun initFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.productModel.collect { uiModel ->
                    println("test12345")
                    uiModel?.let {
                        fillData(uiModel)
                    }
                }
            }
        }
    }
}