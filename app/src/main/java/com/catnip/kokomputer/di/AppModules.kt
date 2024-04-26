package com.catnip.kokomputer.di

import android.content.SharedPreferences
import com.catnip.kokomputer.data.datasource.cart.CartDataSource
import com.catnip.kokomputer.data.datasource.cart.CartDatabaseDataSource
import com.catnip.kokomputer.data.datasource.category.CategoryApiDataSource
import com.catnip.kokomputer.data.datasource.category.CategoryDataSource
import com.catnip.kokomputer.data.datasource.product.ProductApiDataSource
import com.catnip.kokomputer.data.datasource.product.ProductDataSource
import com.catnip.kokomputer.data.datasource.user.UserDataSource
import com.catnip.kokomputer.data.datasource.user.UserDataSourceImpl
import com.catnip.kokomputer.data.repository.CartRepository
import com.catnip.kokomputer.data.repository.CartRepositoryImpl
import com.catnip.kokomputer.data.repository.CategoryRepository
import com.catnip.kokomputer.data.repository.CategoryRepositoryImpl
import com.catnip.kokomputer.data.repository.ProductRepository
import com.catnip.kokomputer.data.repository.ProductRepositoryImpl
import com.catnip.kokomputer.data.repository.UserRepository
import com.catnip.kokomputer.data.repository.UserRepositoryImpl
import com.catnip.kokomputer.data.source.local.database.AppDatabase
import com.catnip.kokomputer.data.source.local.database.dao.CartDao
import com.catnip.kokomputer.data.source.local.pref.UserPreference
import com.catnip.kokomputer.data.source.local.pref.UserPreferenceImpl
import com.catnip.kokomputer.data.source.network.services.KoKomputerApiService
import com.catnip.kokomputer.presentation.cart.CartViewModel
import com.catnip.kokomputer.presentation.checkout.CheckoutViewModel
import com.catnip.kokomputer.presentation.detailproduct.DetailProductViewModel
import com.catnip.kokomputer.presentation.home.HomeViewModel
import com.catnip.kokomputer.presentation.profile.ProfileViewModel
import com.catnip.kokomputer.utils.SharedPreferenceUtils
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModules {

    val networkModule = module {
        single<KoKomputerApiService> { KoKomputerApiService.invoke() }
    }

    //todo : add firebase module

    val localModule = module {
        single<AppDatabase> { AppDatabase.createInstance(androidContext()) }
        single<CartDao> { get<AppDatabase>().cartDao() }
        single<SharedPreferences> {
            SharedPreferenceUtils.createPreference(
                androidContext(),
                UserPreferenceImpl.PREF_NAME
            )
        }
        single<UserPreference> { UserPreferenceImpl(get()) }
    }

    private val datasource = module {
        single<CartDataSource> { CartDatabaseDataSource(get()) }
        single<CategoryDataSource> { CategoryApiDataSource(get()) }
        single<ProductDataSource> { ProductApiDataSource(get()) }
        single<UserDataSource> { UserDataSourceImpl(get()) }
    }

    private val repository = module {
        single<CartRepository> { CartRepositoryImpl(get()) }
        single<CategoryRepository> { CategoryRepositoryImpl(get()) }
        single<ProductRepository> { ProductRepositoryImpl(get()) }
        single<UserRepository> { UserRepositoryImpl(get()) }
    }

    private val viewModelModule = module {
        viewModelOf(::HomeViewModel)
        viewModelOf(::CartViewModel)
        viewModelOf(::CheckoutViewModel)
        viewModel { params ->
            DetailProductViewModel(
                extras = params.get(),
                cartRepository = get()
            )
        }
        viewModelOf(::ProfileViewModel)
    }

    val modules = listOf(
        networkModule,
        localModule,
        datasource,
        repository)

}