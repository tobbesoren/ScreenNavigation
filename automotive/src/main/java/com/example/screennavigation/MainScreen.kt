package com.example.screennavigation

import androidx.car.app.CarContext
import androidx.car.app.Screen
import androidx.car.app.model.Action
import androidx.car.app.model.Row
import androidx.car.app.model.Template
import androidx.car.app.model.*
import androidx.car.app.model.SearchTemplate.SearchCallback
import androidx.car.app.model.signin.InputSignInMethod
import androidx.car.app.model.signin.SignInTemplate
import androidx.core.graphics.drawable.IconCompat


class MainScreen(carContext: CarContext) : Screen(carContext) {
    private fun createListTemplate(): Template {
        val listItems = mutableListOf<String>(
            "List item 1",
            "List Item 2",
            "List Item 3",
            "List Item 4",
            "List Item 5",
            "List Item 6"
        )

        val listBuilder = ItemList.Builder()

        listItems.forEach { item ->
            val listItem = Row.Builder()
                .setTitle(item)
                .build()
            listBuilder.addItem(listItem)
        }

        return ListTemplate.Builder()
            .setTitle("List")
            .setSingleList(listBuilder.build())
            .setHeaderAction(Action.BACK)
            .build()
    }

    private fun createSignInTemplate() : Template {

        val callback = object : InputCallback{
            override fun onInputSubmitted(text: String) {
                super.onInputSubmitted(text)
            }

            override fun onInputTextChanged(text: String) {
                super.onInputTextChanged(text)
            }
        }

        val passwordInput = InputSignInMethod.Builder(callback)
            .setHint("password")
            .setInputType(InputSignInMethod.INPUT_TYPE_PASSWORD)
            .build()

        return SignInTemplate.Builder(passwordInput)
            .setTitle("SignInTemplate")
            .setInstructions("Enter your password")
            .setHeaderAction(Action.BACK)
            .build()


    }

    private fun createMessageTemplate() : Template {

        return MessageTemplate.Builder("This is an important message!")
            .setHeaderAction(Action.BACK)
            .build()

    }

    private fun createLongMessageTemplate() : Template {
        return LongMessageTemplate.Builder(
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message." +
                "This is a very long extremely important message.")
            .setHeaderAction(Action.BACK)
            .build()
    }

    private fun createSearchTemplate() : Template {
        val listBuilder = ItemList.Builder()
        for (i in 1..5) {
            listBuilder.addItem(
                Row.Builder()
                    .setTitle("Result $i")
                    .addText("A search result")
                    .build()
            )
        }

        val searchListener = object : SearchCallback {
            override fun onSearchTextChanged(searchText: String) {
            }

            override fun onSearchSubmitted(searchText: String) {
            }
        }
        return SearchTemplate.Builder(searchListener)
            .setHeaderAction(Action.BACK)
            .setShowKeyboardByDefault(false)
            .setItemList(listBuilder.build())
            .build()
    }

    /*private fun createPaneTemplate() : Template {

    }

    private fun createMapTemplate() : Template {

    }

    private fun createPlaceListMapTemplate() : Template {

    }

    private fun createPlaceListNavigationTemplate() : Template {

    }

    private fun createRoutePreviewTemplate() : Template {

    }

    private fun createNavigationTemplate() : Template {

    }
*/
    override fun onGetTemplate(): Template {
        val listTemplate = createListTemplate()
        val signInTemplate = createSignInTemplate()
        val messageTemplate = createMessageTemplate()
        val longMessageTemplate = createLongMessageTemplate()
        val searchTemplate = createSearchTemplate()

        val optionList = mutableListOf(option(
            listTemplate,
            "List Template",
            "A template for displaying lists",
            R.drawable.baseline_density_small_24),
            option(
                signInTemplate,
                "Sign In Template",
                "A template for signing in",
                R.drawable.baseline_login_24),
            option(
                messageTemplate,
                "Message Template",
                "A template for messages",
                R.drawable.baseline_message_24),
            option(
                longMessageTemplate,
                "Long Message Template",
                "A template for long messages",
                R.drawable.baseline_message_24),
            option(
                searchTemplate,
                "Search Template",
                "A template for searching",
                R.drawable.baseline_search_24),

            )








        val gridListBuilder = ItemList.Builder()

        optionList.forEach() { option ->
            val gridItemBuilder = GridItem.Builder()
                .setTitle(option.name)
                .setOnClickListener{
                    screenManager.push(DetailScreen(carContext, option.template))
                }
                .setImage(
                    CarIcon.Builder(
                        IconCompat.createWithResource(
                            carContext,
                            option.icon
                        )
                    ).build()
                )
                .build()
            gridListBuilder.addItem(gridItemBuilder)
        }

        val messageButton = Action.Builder()
            .setIcon(
                CarIcon.ALERT
            )
            .setOnClickListener(){
                screenManager.push(MessageScreen(carContext))
            }.build()

        val actionStrip = ActionStrip.Builder()
            .addAction(messageButton)
            .build()

        return GridTemplate.Builder()
            .setTitle("All options")
            .setSingleList(gridListBuilder.build())
            .setActionStrip(actionStrip)
            .build()
    }
}

class option(val template: Template,
             val name: String,
             val description: String?,
             val icon : Int)