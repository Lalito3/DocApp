package com.example.docapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.docapp.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM usuarios WHERE correo =:correo AND contrasena =:contrasena LIMIT 1")
    suspend fun obtenerUsuario(correo: String, contrasena: String): User?
}