package com.example.encuestacompost.daoSQLite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.example.encuestacompost.entidad.Encuestador
import com.example.encuestacompost.entidad.EncuestadorConEncuesta
import kotlinx.coroutines.flow.Flow

@Dao
interface EncuestadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun alta(encuestador: Encuestador)

    @Update
    suspend fun actualziar(encuestador: Encuestador)

    @Delete
    suspend fun baja(encuestador: Encuestador)

    @Upsert
    suspend fun altaActualziar(encuestador: Encuestador)

    @Query("SELECT * FROM Encuestador")
    fun listarTodo(): Flow<List<Encuestador>>

    @Query("SELECT * FROM Encuestador WHERE cue = :cue")
    suspend fun listarPorId(cue: String): Encuestador

    @Query("SELECT * FROM Encuestador WHERE cue = :cue AND password = :pass")
    suspend fun login(cue: String, pass: String): Encuestador

    @Transaction
    @Query("SELECT * FROM Encuestador")
    suspend fun listarTodoEncuestadorConEncuesta(): List<EncuestadorConEncuesta>

    @Transaction
    @Query("SELECT * FROM Encuestador WHERE cue = :cue")
    suspend fun listarEncuestadorConEncuestaPorId(cue: String): EncuestadorConEncuesta

}