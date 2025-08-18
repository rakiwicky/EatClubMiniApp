package rw.eatclubminiapp.feature.resturants.internal.data.remote.dto

import com.google.gson.annotations.SerializedName


internal data class RestaurantsDto(
    @SerializedName("restaurants") val restaurants: List<RestaurantDto>
) {
    data class RestaurantDto(
        @SerializedName("objectId") val objectId: String,
        @SerializedName("name") val name: String,
        @SerializedName("address1") val address1: String,
        @SerializedName("suburb") val suburb: String,
        @SerializedName("cuisines") val cuisines: List<String>,
        @SerializedName("imageLink") val imageLink: String,
        @SerializedName("open") val open: String,
        @SerializedName("close") val close: String,
        @SerializedName("deals") val dealDtos: List<DealDto>
    ) {
        data class DealDto(
            @SerializedName("objectId") val objectId: String,
            @SerializedName("discount") val discount: String?,
            @SerializedName("dineIn") val dineIn: Boolean?,
            @SerializedName("lightning") val lightning: Boolean?,
            @SerializedName("open") val open: String?,
            @SerializedName("close") val close: String?,
            @SerializedName("qtyLeft") val qtyLeft: String
        )
    }
}