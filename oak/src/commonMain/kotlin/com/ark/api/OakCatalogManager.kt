package com.ark.api

import com.ark.core.domain.ApiResponse
import com.ark.core.domain.DataError
import com.ark.domain.model.MarketPlace
import com.ark.domain.model.ProductCatalog
import com.ark.domain.model.SearchFilter
import com.ark.domain.usecase.ProductCatalogUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OakCatalogManager : KoinComponent {

    private val productCatalogUseCase: ProductCatalogUseCase by inject()

    suspend fun fetchCatalog(
        query: String,
        marketPlaces: List<MarketPlace>,
        filter: SearchFilter = SearchFilter.FEATURED,
        page: Int = 1
    ): ApiResponse<List<ProductCatalog>, DataError> {

        val uniqueMarketplaces = marketPlaces.toSet()
        return productCatalogUseCase.fetchProductCatalog(
            query = query,
            page = page,
            filter = filter,
            marketPlaces = uniqueMarketplaces
        )
    }
}