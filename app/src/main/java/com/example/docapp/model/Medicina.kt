package com.example.docapp.model
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;


data class Medicina (
    @StringRes val  stringResourceId: Int,
    val nombre_med:String,
    val costo: String,
    @DrawableRes val drawableResId: Int


)