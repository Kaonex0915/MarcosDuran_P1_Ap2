package edu.ucne.marcosduran_p1_ap2.presentation.tarea

sealed interface TareaEvent{
    data class TareaIdChange(val tareaId: Int): TareaEvent
    data class DescripcionChange(val descripcion:String): TareaEvent
    data class TiempoChange(val tiempo: Int): TareaEvent
    data object Save: TareaEvent
    data object Delete: TareaEvent
    data object New: TareaEvent

}
