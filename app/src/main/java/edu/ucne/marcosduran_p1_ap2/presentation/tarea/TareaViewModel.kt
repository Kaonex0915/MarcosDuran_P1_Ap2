package edu.ucne.marcosduran_p1_ap2.presentation.tarea

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.marcosduran_p1_ap2.data.local.entities.TareaEntity
import edu.ucne.marcosduran_p1_ap2.data.repository.TareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TareaViewModel @Inject constructor(
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
    private fun save() {
        viewModelScope.launch {
            if (_uiState.value.descripcion.isNullOrBlank()){
                _uiState.update {
                    it.copy(errorMessage = "El campo esta vacio")
                }
                return@launch
            }

            try {
                tareaRepository.save(_uiState.value.toEntity())

                _uiState.update {
                    it.copy(errorMessage = null)
                }
            }catch (e: Exception){
                _uiState.update {
                    it.copy(errorMessage = "Error al guardar: ${e.message}")

                }
            }
        }
    }

    private fun nuevo() {
        _uiState.update {
            it.copy(
                tareaId = null,
                descripcion = "",
                tiempo = 0,
                errorMessage = null
            )
        }
    }

    fun selectedTarea(tareaId: Int){
        viewModelScope.launch {
            if(tareaId > 0){
                try {
                    val tarea = tareaRepository.getTarea(tareaId)
                    _uiState.update {
                        it.copy(
                            tareaId = tarea?.tareaId,
                            descripcion = tarea?.descripcion ?: "",
                            tiempo = tarea?.tiempo,
                            errorMessage = null
                        )
                    }
                } catch (e: Exception) {
                    _uiState.update {
                        it.copy(errorMessage = "Error al cargar sistema: ${e.message}")
                    }
                }
            }
        }
    }

    private fun delete() {
        viewModelScope.launch {
            try {
                tareaRepository.delete(_uiState.value.toEntity())
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(errorMessage = "Error al eliminar: ${e.message}")
                }
            }
        }
    }

    private fun getTareas() {
        viewModelScope.launch {
            tareaRepository.getTareas().collect { tareas ->
                _uiState.update {
                    it.copy(tareas = tareas)
                }
            }
        }
    }

    private fun onDecripcionChange(descripcion: String) {
        _uiState.update {
            it.copy(
                descripcion = descripcion,
                errorMessage = null
            )
        }
    }
    private fun onTiempoChange(tiempo: Long) {
        _uiState.update {
            it.copy(
                tiempo = tiempo,
                errorMessage = null
            )
        }
    }

    private fun onTareaIdChange(tareaId: Int) {
        _uiState.update {
            it.copy(tareaId = tareaId)
        }
    }


}
fun TareaUiState.toEntity() = TareaEntity(
    tareaId = tareaId,
    descripcion = descripcion ?: "",
    tiempo = tiempo,
)
