package com.example.docapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.docapp.model.Cita
import com.example.docapp.model.User

@Database(entities = [User::class, Cita::class], version = 2, exportSchema = false)
abstract class DocAppDataBase: RoomDatabase() {
    abstract fun usuarioDao(): UserDao
    abstract fun citaDao(): CitaDao

    companion object{
        @Volatile
        private var INSTANCE: DocAppDataBase? = null

        fun getDataBAse(context: Context): DocAppDataBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DocAppDataBase::class.java,
                    "docapp_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}