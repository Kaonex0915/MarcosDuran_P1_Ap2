package edu.ucne.marcosduran_p1_ap2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.marcosduran_p1_ap2.data.local.dao.TareaDao
import edu.ucne.marcosduran_p1_ap2.data.local.entities.TareaEntity

@Database(
    entities = [
        TareaEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class TareaDb : RoomDatabase() {
    abstract fun TareaDao(): TareaDao
}
