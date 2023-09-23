package com.example.littlelemon

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.littlelemon.ui.theme.LittleLemonCharcoal
import com.example.littlelemon.ui.theme.LittleLemonGreen
import com.example.littlelemon.ui.theme.LittleLemonLightGrey
import com.example.littlelemon.ui.theme.LittleLemonYellow
import com.example.littlelemon.ui.theme.karlaFamily
import com.example.littlelemon.ui.theme.markaziTextFamily

@Composable
fun HomeScreen(database: AppDatabase) {
    val menuCategories: List<String> = listOf("Starters", "Mains", "Desserts", "Drinks")
    val databaseMenuItems = database.MenuItemDao().getAll().observeAsState(emptyList())

    val (menuFilter, setMenuFilter) = remember { mutableStateOf("") }
    val (searchPhrase, setSearchPhrase) = remember { mutableStateOf("") }

    val menuItems: List<MenuItemRoom> = when(menuFilter)
    {
        "" -> {
            when(searchPhrase){
                "" -> {databaseMenuItems.value}
                else -> {
                    databaseMenuItems.value.filter {
                        it.title.contains(searchPhrase, true)
                    }
                }
            }

        }
        else -> {
            when(searchPhrase){
                "" -> {
                    databaseMenuItems.value.filter {
                        it.category.compareTo(menuFilter, true) == 0
                    }
                }
                else -> {
                    databaseMenuItems.value.filter {
                        (it.title.contains(searchPhrase, true) &&
                        it.category.compareTo(menuFilter, true) == 0)
                    }
                }
            }
        }
    }


    Column(
         modifier = Modifier
             .fillMaxHeight()
             .background(LittleLemonLightGrey)
    )
        {
        PresentationCard(searchPhrase, setSearchPhrase)
        MenuFilterBar(menuCategories, setMenuFilter)
        MenuItems(menuItems)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuFilterBar(menuCategories: List<String>, setMenuFilter: (String) -> Unit ) {
    val (startersFilterSelected, setStartersFilterSelected) = remember { mutableStateOf(false) }
    val (mainsFilterSelected, setMainsFilterSelected) = remember { mutableStateOf(false) }
    val (dessertsFilterSelected, setDessertsFilterSelected) = remember { mutableStateOf(false) }
    val (drinksFilterSelected, setDrinksFilterSelected) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 5.dp)
    ){
        Text(
            text = "ORDER FOR DELIVERY!",
            style = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LittleLemonCharcoal,
            )
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp)
        ) {
            menuCategories.forEach {
                FilterChip(
                    shape = RoundedCornerShape(20.dp),
                    selected =
                        when (it) {
                            "Starters" -> { startersFilterSelected }
                            "Mains" -> { mainsFilterSelected }
                            "Desserts" -> { dessertsFilterSelected }
                            "Drinks" -> { drinksFilterSelected }
                            else -> false
                        },
                    onClick = {
                        when (it) {
                            "Starters" -> {
                                setStartersFilterSelected(!startersFilterSelected)
                                if(!startersFilterSelected)
                                {
                                    setMainsFilterSelected(false)
                                    setDessertsFilterSelected(false)
                                    setDrinksFilterSelected(false)
                                    setMenuFilter(it)
                                }
                                else{
                                    setMenuFilter("")
                                }
                            }
                            "Mains" -> {
                                setMainsFilterSelected(!mainsFilterSelected)
                                if(!mainsFilterSelected)
                                {
                                    setStartersFilterSelected(false)
                                    setDessertsFilterSelected(false)
                                    setDrinksFilterSelected(false)
                                    setMenuFilter(it)
                                }
                                else{
                                    setMenuFilter("")
                                }
                            }
                            "Desserts" -> {
                                setDessertsFilterSelected(!dessertsFilterSelected)
                                if(!dessertsFilterSelected)
                                {
                                    setStartersFilterSelected(false)
                                    setMainsFilterSelected(false)
                                    setDrinksFilterSelected(false)
                                    setMenuFilter(it)
                                }
                                else{
                                    setMenuFilter("")
                                }
                            }
                            "Drinks" -> {
                                setDrinksFilterSelected(!drinksFilterSelected)
                                if(!drinksFilterSelected)
                                {
                                    setStartersFilterSelected(false)
                                    setMainsFilterSelected(false)
                                    setDessertsFilterSelected(false)
                                    setMenuFilter(it)
                                }
                                else{
                                    setMenuFilter("")
                                }
                            }
                        }
                    },
                    label = {
                        Text(
                            text = it,
                            style = TextStyle(
                                fontFamily = karlaFamily,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = LittleLemonGreen
                            )
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color.LightGray
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Color.Transparent,
                        selectedBorderColor = LittleLemonGreen
                    )
                )
            }
        }
        Divider(
            thickness = 1.dp,
            color = LittleLemonGreen,
            modifier = Modifier
                .padding(bottom = 0.dp)
        )
    }
}

@Composable
fun MenuItems(
    items: List<MenuItemRoom>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 15.dp)
    ) {
        LazyColumn {
            itemsIndexed(items) { _, item ->
                MenuItemDetails(item)
            }
        }
    }
}

@Composable
fun MenuItemDetails(menuItem: MenuItemRoom) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = menuItem.title,
            style = TextStyle(
                fontFamily = karlaFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(.65f)
            ) {
                Text(
                    text = menuItem.description,
                    style = TextStyle(
                        fontFamily = karlaFamily,
                        fontSize = 15.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "\$${menuItem.price}.00",
                    style = TextStyle(
                        fontFamily = karlaFamily,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .padding(top = 15.dp, bottom = 10.dp)
                )
            }
            Image(
                painter = rememberAsyncImagePainter(menuItem.imageURL),
                contentDescription = "Translated description of what the image contains",
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(.85f)
                    .height(100.dp)
            )
        }
        Divider(
            thickness = 1.dp,
            color = LittleLemonGreen,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationCard(searchPhrase: String, setSearchPhrase: (String) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .background(color = LittleLemonGreen))
    {
        Box{
            Text(
                text = "Little Lemon",
                style = TextStyle(
                    fontSize = 62.sp,
                    fontFamily = markaziTextFamily,
                    color = LittleLemonYellow,
                ),
                modifier = Modifier
                    .padding(start = 15.dp, top = 10.dp, bottom = 0.dp)
            )
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 60.dp, bottom = 0.dp, end = 0.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.70f)
                        .padding(top = 0.dp)
                ) {
                    Text(
                        text = "Chicago",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontFamily = markaziTextFamily,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(start = 0.dp, top = 0.dp, bottom = 20.dp)
                    )
                    Text(
                        text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                        style = TextStyle(
                            fontSize = 17.sp,
                            fontFamily = karlaFamily,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(start = 0.dp, top = 0.dp, bottom = 20.dp, end = 30.dp)
                    )

                }
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Restaurant dish",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .width(190.dp)
                        .padding(start = 225.dp, top = 32.dp, bottom = 20.dp, end = 15.dp)
                        .clip(
                            shape = RoundedCornerShape(10.dp)
                        )
                )
            }
        }
        TextField(
            value = searchPhrase,
            onValueChange = {setSearchPhrase(it)},
            modifier = Modifier
                .padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 10.dp)
                .fillMaxWidth()
                .border(
                    border = BorderStroke(2.dp, LittleLemonYellow),
                    shape = RoundedCornerShape(10.dp)
                ),
            textStyle = TextStyle(
                fontFamily = karlaFamily,
                fontSize = 18.sp
            ),
            placeholder = {
                Text(
                    text = "Enter search phrase",
                    fontFamily = karlaFamily,
                    fontSize = 18.sp,
                    color = Color.Gray)
            },
            leadingIcon = { Icon(
                Icons.Filled.Search,
                contentDescription = null,
                tint = LittleLemonCharcoal
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = LittleLemonCharcoal,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                containerColor = LittleLemonLightGrey
            ),
            shape =RoundedCornerShape(10.dp)
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(LittleLemonLightGrey)
    ){
        PresentationCard("") {}
        MenuFilterBar(listOf("Starters","Mains","Desserts","Drinks"), setMenuFilter = {})
        MenuItems(listOf(MenuItemRoom(
            id = 1,
            title = "Greek Salad",
            description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            price = "10".toInt(),
            imageURL = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
            category = "starters")))
    }
}
