package com.catnip.kokomputer.data.datasource.category

import com.catnip.kokomputer.data.source.network.model.category.CategoriesResponse
import com.catnip.kokomputer.data.source.network.services.KoKomputerApiService

class CategoryApiDataSource(
    private val service: KoKomputerApiService
) : CategoryDataSource {
    override suspend fun getCategories(): CategoriesResponse {
        return service.getCategories()
    }
}