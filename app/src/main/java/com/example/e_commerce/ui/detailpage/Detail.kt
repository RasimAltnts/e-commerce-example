package com.example.e_commerce.ui.detailpage

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.e_commerce.databinding.FragmentDetailBinding
import com.example.e_commerce.R
import com.google.android.material.appbar.MaterialToolbar

class Detail : Fragment() {

    private val viewModel: DetailViewModel by viewModels()
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
        fillData()
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
    private fun fillData() {
        binding.productTitle.text = args.productModel.name
        binding.productDesc.text = args.productModel.desc
        binding.favoriteIcon.isSelected = args.productModel.isFavorite
        binding.priceTextView.text = args.productModel.price + "₺"

    }
}