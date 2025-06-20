package com.molks.ecommerce.presentation.screens.product_detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molks.ecommerce.common.Constrains
import com.molks.ecommerce.domain.use_case.get_product_detail.GetProductDetailUseCase
import com.molks.ecommerce.common.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    //state
    private val _state = mutableStateOf<ProductDetailState>(ProductDetailState())
    val state: State<ProductDetailState> = _state

    init {

        val productId = stateHandle.get<String>(Constrains.PRODUCT_ID_PARAM)
        getProductDetail(productId!!.toInt())
    }

    private fun getProductDetail(productId: Int) {
        getProductDetailUseCase(productId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ProductDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ProductDetailState(productDetail = result.data)

                }
                is Resource.Error -> {
                    _state.value = ProductDetailState(errorMessage = result.message!!)
                }
            }

        }.launchIn(viewModelScope)
    }
}