package rw.eatclubminiapp.feature.resturants.internal.presentation.detail.model

internal data class RestaurantDetailItem(
    val name: String,
    val suburb: String,
    val openHours: String,
    val cuisines: List<String>,
    val imageLink: String,
    val deals: List<DealItem>
) {
    data class DealItem(
        val discount: String,
        val availabilityInfo: String?,
        val qtyLeft: String
    )
}