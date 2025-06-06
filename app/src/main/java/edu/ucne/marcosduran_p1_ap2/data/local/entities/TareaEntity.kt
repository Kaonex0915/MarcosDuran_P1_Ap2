package edu.ucne.marcosduran_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Tarea")
data class TareaEntity(
    @PrimaryKey
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Long? = null
)
