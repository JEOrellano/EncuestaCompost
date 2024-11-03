package com.example.encuestacompost.daoSQLite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.encuestacompost.entidad.Encuesta
import kotlinx.coroutines.flow.Flow

@Dao
interface EncuestaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun alta(encuesta: Encuesta)

    @Update
    suspend fun actualizar(encuesta: Encuesta)

    @Delete
    suspend fun baja(encuesta: Encuesta)

    @Upsert
    suspend fun altaActualizar(encuesta: Encuesta)

    @Query("SELECT * FROM Encuesta")
    fun listarTodo(): Flow<List<Encuesta>>

    @Query("SELECT * FROM Encuesta WHERE id = :id")
    suspend fun listarPorId(id: Int): Encuesta

    @Query("UPDATE sqlite_sequence SET seq = 0 WHERE name = 'Encuesta'")
    suspend fun resetAutoIncrement()
}