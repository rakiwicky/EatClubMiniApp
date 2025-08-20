package rw.eatclubminiapp.feature.resturants.internal.di

import android.content.Context
import android.content.res.Resources
import com.eatclubminiapp.library.network.RestServiceBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import rw.eatclubminiapp.feature.resturants.internal.data.remote.api.RestaurantApiService
import rw.eatclubminiapp.feature.resturants.internal.data.remote.api.RestaurantRemoteDataSource
import rw.eatclubminiapp.feature.resturants.internal.data.remote.repository.RestaurantRepositoryImpl
import rw.eatclubminiapp.feature.resturants.internal.domain.repository.RestaurantRepository
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.GetRestaurantDetailUseCase
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.GetRestaurantsUseCase
import rw.eatclubminiapp.feature.resturants.internal.domain.usecase.SearchRestaurantsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RestaurantModule {

    @Binds
    @Singleton
    abstract fun bindsRestaurantRepository(
        restaurantRepositoryImpl: RestaurantRepositoryImpl
    ): RestaurantRepository

    companion object {
        @Provides
        @Singleton
        fun provideRemoteDataSource(api: RestaurantApiService): RestaurantRemoteDataSource {
            return RestaurantRemoteDataSource(api)
        }

        @Provides
        @Singleton
        fun providesRestaurantApiService(
            restServiceBuilder: RestServiceBuilder
        ): RestaurantApiService {
            return restServiceBuilder.create(RestaurantApiService::class.java)
        }

        @Provides
        @Singleton
        fun providesResources(
            @ApplicationContext context: Context
        ): Resources {
            return context.resources
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
internal object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providesGetRestaurantsUseCase(repository: RestaurantRepository): GetRestaurantsUseCase {
        return GetRestaurantsUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun providesGetRestaurantDetailUseCase(repository: RestaurantRepository): GetRestaurantDetailUseCase {
        return GetRestaurantDetailUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun providesSearchRestaurantsUseCase(repository: RestaurantRepository): SearchRestaurantsUseCase {
        return SearchRestaurantsUseCase(repository)
    }
}