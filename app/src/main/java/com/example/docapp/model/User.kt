package com.example.docapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "edad")
    val edad: Int,
    @ColumnInfo(name = "estatura")
    val estatura: Float,
    @ColumnInfo(name = "peso")
    val peso: Float,
    @ColumnInfo(name = "alergias")
    val alergias: String,
    @ColumnInfo(name = "correo")
    val correo: String,
    @ColumnInfo(name = "contrasena")
    val contrasena: String
)