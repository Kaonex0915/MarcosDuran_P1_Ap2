package edu.ucne.marcosduran_p1_ap2.presentation.tarea

sealed class TareaEvent{
    data class TareaIdChange(val tareaId: Int): TareaEvent()
    data class DescripcionChange(val descripcion:String): TareaEvent()
    data class TiempoChange(val tiempo: Long): TareaEvent()
    data object Save: TareaEvent()
    data object Delete: TareaEvent()
    data object New: TareaEvent()

}
