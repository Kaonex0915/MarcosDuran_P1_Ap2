package edu.ucne.marcosduran_p1_ap2.data.repository

import edu.ucne.marcosduran_p1_ap2.data.local.dao.TareaDao
import edu.ucne.marcosduran_p1_ap2.data.local.entities.TareaEntity
import javax.inject.Inject

class TareaRepository @Inject constructor(
    private val tareaDouble: Double
) {
    suspend fun save(tarea: TareaEntity) = TareaDao.save(tarea)

    suspend fun getTarea(id: Int) = TareaDao.find(id)

    suspend fun delete(tarea: TareaEntity) = TareaDao.delete(tarea)

    fun getTarea() = TareaDao.getAll()
}
