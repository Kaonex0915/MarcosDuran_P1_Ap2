package edu.ucne.marcosduran_p1_ap2.presentation.tarea

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.marcosduran_p1_ap2.data.repository.TareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject



@HiltViewModel
class SystemViewModel @Inject constructor(
    private val tareaRepository: TareaRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TareaUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getTareas()
    }

    fun onEvent(event: TareaEvent) {
        when (event) {
            is TareaEvent.TareaIdChange -> onTareaIdChange(event.tareaId)
            is TareaEvent.DescripcionChange -> onDecripcionChange(event.descripcion)
            is TareaEvent.TiempoChange -> onTiempoChange(event.tiempo)
            TareaEvent.Save -> save()
            TareaEvent.Delete -> delete()
            TareaEvent.New -> nuevo()
        }
    }
}
