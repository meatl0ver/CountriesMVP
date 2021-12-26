package raul.imashev.room.dao

import androidx.room.*
import raul.imashev.room.model.RoomCountry


@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(countries: List<RoomCountry>)


    @Query("SELECT * FROM RoomCountry")
    fun getAll(): List<RoomCountry>
}