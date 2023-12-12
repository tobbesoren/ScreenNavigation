package com.example.screennavigation

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.car.app.model.*
import androidx.core.graphics.drawable.IconCompat

class HelloWorldScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val row = Row.Builder().setTitle("Hello world!").build()
        val pane = Pane.Builder().addRow(row).build()
        return PaneTemplate.Builder(pane)
            .setHeaderAction(Action.APP_ICON)
            .build()
    }
}


class MainScreen(carContext: CarContext) : Screen(carContext) {
    override fun onGetTemplate(): Template {
        val optionArray = mutableListOf<String>(
            "Option 1",
            "Option 2",
            "Option 3",
            "Option 4",
            "Option 5",
            "Option 6")

        val gridItemListBuilder = ItemList.Builder()

        optionArray.forEach() { option ->
            val gridItemBuilder = GridItem.Builder()
                .setTitle(option)
                .setOnClickListener{
                    screenManager.push(DetailScreen(carContext, option))
                }
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(
                            carContext,
                            R.drawable.baseline_emoji_objects_24
                        )
                    ).build()
                )
                .build()
            gridItemListBuilder.addItem(gridItemBuilder)
        }
        return GridTemplate.Builder()
            .setTitle("All options")
            .setSingleList(gridItemListBuilder.build())
            .build()
    }

}