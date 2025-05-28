package com.example.docapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citas")
data class Cita (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "usuario_id") val usuarioId: Int,
    @ColumnInfo(name = "medico_nombre") val medicoNombre: String,
    @ColumnInfo(name = "fecha") val fecha: String,
    @ColumnInfo(name = "hora") val hora: String,
)