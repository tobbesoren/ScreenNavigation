package com.example.screennavigation

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

/*class DetailScreen(carContext: CarContext, private val option: String) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val row = Row.Builder().setTitle(option).build()
        val pane = Pane.Builder().addRow(row).build()
        return PaneTemplate.Builder(pane)
            .setTitle("Details")
            .setHeaderAction(Action.BACK)
            .build()

    }
}*/

class DetailScreen(carContext: CarContext, private val template: Template) : Screen(carContext) {

    override fun onGetTemplate(): Template {

        return template

    }
}