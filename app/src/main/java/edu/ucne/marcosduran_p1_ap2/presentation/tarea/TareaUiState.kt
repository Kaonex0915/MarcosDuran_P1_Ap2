package edu.ucne.marcosduran_p1_ap2.presentation.tarea
import edu.ucne.marcosduran_p1_ap2.data.local.entities.TareaEntity

data class TareaUiState(
    val tareaId: Int? = null,
    val descripcion: String? = "",
    val tiempo: Long? = null,
    val errorMessage: String? = null,
    val tareas: List<TareaEntity> = emptyList(),
    val guardadoConExito: Boolean = false

)