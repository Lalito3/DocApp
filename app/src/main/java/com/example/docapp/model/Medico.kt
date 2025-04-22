package com.example.docapp.model

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

data class Medico(
    @StringRes val  stringResourceId: Int,
    val nombre_med:String,
    val especialidad: String,
    @DrawableRes val drawableResId: Int


)