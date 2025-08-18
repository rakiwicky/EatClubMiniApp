package rw.eatclubminiapp.feature.resturants.internal.data.remote.api

import retrofit2.http.GET
import rw.eatclubminiapp.feature.resturants.internal.data.remote.dto.RestaurantsDto

internal interface RestaurantApiService {

    @GET("misc/challengedata.json")
    suspend fun getRestaurants(): RestaurantsDto
}