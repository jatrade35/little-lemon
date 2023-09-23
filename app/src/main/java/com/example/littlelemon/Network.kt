package com.example.littlelemon

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(

    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(

    @SerialName("id")
    val id: Int,

    @SerialName("title")
    var title: String,

    @SerialName("description")
    var description: String,

    @SerialName("price")
    var price: Int,

    @SerialName("image")
    var image: String,

    @SerialName("category")
    var category: String

) {
    fun toMenuItemRoom() = MenuItemRoom(
        id,
        title,
        description,
        price,
        image,
        category
    )
}
