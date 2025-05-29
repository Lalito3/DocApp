package com.example.docapp.viewmodel

import android.app.Application
import android.app.SharedElementCallback
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.docapp.model.User
import com.example.docapp.room.DocAppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class UserViewModel(application: Application): AndroidViewModel(application) {
    private val userDao = DocAppDataBase.getDataBAse(application).usuarioDao()

    fun insertarUsuario(user: User, onSucces:() -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insertUser(user)
            delay(5000)
            launch(Dispatchers.Main) {
                onSucces()
            }
        }
    }

    fun mostrarToast(mensaje: String){
        Toast.makeText(getApplication(), mensaje, Toast.LENGTH_LONG).show()
    }

    fun validarCredenciales( correo: String, contrasena: String, callback: (User?) -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            val usuario = userDao.obtenerUsuario(correo, contrasena)
            withContext(Dispatchers.Main) {
                callback(usuario)
            }
        }
    }
}