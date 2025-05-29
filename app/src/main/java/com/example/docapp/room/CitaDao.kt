package com.example.docapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.docapp.model.Cita

@Dao
interface CitaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCita(cita: Cita)

    @Query("SELECT * FROM citas WHERE usuario_id = :usuarioId")
    suspend fun obtenerCitasDeUsuario(usuarioId: Int): List<Cita>

    @Delete
    fun deleteCita(cita: Cita)
}