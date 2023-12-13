package com.example.screennavigation

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.CarIcon
import androidx.car.app.model.Pane
import androidx.car.app.model.PaneTemplate
import androidx.car.app.model.Row
import androidx.car.app.model.Template

class MessageScreen(carContext: CarContext) : Screen(carContext) {

    override fun onGetTemplate(): Template {
        val row1 = Row.Builder().setTitle("Message").build()
        val row2 = Row.Builder().setTitle("Important message!").build()
        val okButton = Action.Builder()
            .setIcon(
                CarIcon.BACK
            )
            .setOnClickListener{
                finish()
            }
            .build()

        val pane = Pane.Builder()
            .addRow(row1)
            .addRow(row2)
            .addAction(okButton)
            .build()

        return PaneTemplate.Builder(pane)
            .setTitle("Message")
            .setHeaderAction(Action.BACK)
            .build()

    }
}