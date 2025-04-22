package com.example.docapp.data



import com.example.docapp.model.Medico
import com.example.docapp.R

class DataDocSource {
    fun loadDoctors(): List<Medico> {
        return listOf<Medico>(
            Medico(R.string.doctor1, nombre_med = "Felipe De Jesús", especialidad = "Médico Cirujano", drawableResId = R.drawable.d1),
            Medico(R.string.doctor2, "Jocelyn Hof Reyes", "Ginecóloga",
                drawableResId = R.drawable.d2
            ),
            Medico(
                stringResourceId = R.string.doctor3,
                nombre_med = "Renata María López Peréz",
                especialidad = "Médica Traumatóloga",
                drawableResId = R.drawable.d3
            ),
            Medico(
                stringResourceId = R.string.doctor4,
                nombre_med = "Zoé Agatha Flores",
                especialidad = "Médica Pediatra",
                drawableResId = R.drawable.d4
            ),
            Medico(
                stringResourceId = R.string.doctor5,
                nombre_med = "Axel Mungía Pertil",
                especialidad = "Médico familiar",
                drawableResId = R.drawable.d5
            )
        )
    }
}