package com.example.docapp.data

import com.example.docapp.model.Medicina
import com.example.docapp.R

class DataMedicineSource {
    fun loadMedicines(): List<Medicina> {
        return listOf<Medicina>(
            Medicina(stringResourceId = R.string.medicina1, nombre_med = "Paracentamol", costo = "$22", drawableResId = R.drawable.md1),
            Medicina(R.string.medicina5, "Ibuprofeno", "$201",
                drawableResId = R.drawable.md5
            ),
            Medicina(
                stringResourceId = R.string.medicina2,
                nombre_med = "Omeprazol",
                costo = "$55",
                drawableResId = R.drawable.md2
            ),
            Medicina(
                stringResourceId = R.string.medicina3,
                nombre_med = "Salbutamol",
                costo = "$88",
                drawableResId = R.drawable.md3
            ),
            Medicina(
                stringResourceId = R.string.medicina4,
                nombre_med = "Aspirina",
                costo = "$122",
                drawableResId = R.drawable.md4
            )
        )
    }
}
