package com.example.app_database_room.state

data class CronoState(
    val cronometroActivo : Boolean = false,
    val showSaveButton: Boolean = false,
    val showTextField : Boolean = false,
    var title : String = ""
)
