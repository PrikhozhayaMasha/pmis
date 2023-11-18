package com.prikhozhayamaria.pmis.deardogs.myapplication.classes.home.data.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.prikhozhayamaria.pmis.deardogs.myapplication.di.DogEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
/**
 * Data Access Object for the memories table.
 */
@Dao
interface DogsDao {

    /**
     * Observes list of memories.
     *
     * @return all memories.
     */
    @Query("SELECT * FROM Dogs")
    fun observeDogs(): Flow<List<DogEntity>>

    /**
     * Observes a single memory.
     *
     * @param id the memory id.
     * @return the planet with planetId.
     */
    @Query("SELECT * FROM Dogs WHERE id = :id")
    fun observeDogById(id: UUID): Flow<DogEntity?>

    /**
     * Select all planets from the planets table.
     *
     * @return all planets.
     */
    /* @Query("SELECT * FROM Memories")
     suspend fun getMemories(): List<MemoryEntity>*/

    /**
     * Select a planet by id.
     *
     * @param planetId the planet id.
     * @return the planet with planetId.
     */
    @Query("SELECT * FROM Dogs WHERE id = :id")
    suspend fun getDogById(id: UUID): DogEntity?

    /**
     * Insert a planet in the database. If the planet already exists, replace it.
     *
     * @param planet the planet to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: DogEntity)

    /**
     * Update a planet.
     *
     * @param planet planet to be updated
     * @return the number of planets updated. This should always be 1.
     */
    @Update
    suspend fun updateDog(dog: DogEntity): Int

    /**
     * Delete a planet by id.
     *
     * @return the number of planets deleted. This should always be 1.
     */
    @Query("DELETE FROM Dogs WHERE id = :id")
    suspend fun deleteDogById(id: UUID): Int

    /**
     * Delete all planets.
     */
    @Query("DELETE FROM Dogs")
    suspend fun deleteDogs()

    @Transaction
    suspend fun setDogs(planets: List<DogEntity>) {
        deleteDogs()
        planets.forEach { insertDog(it) }
    }
}