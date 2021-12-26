package raul.imashev.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCountry(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    var area: Double,
    var capital: String,
    var continents: String,
    var population: Int,
    var region: String,
    var fifa: String,
    var flag: String,
    var commonName: String,
    var officialName: String
)
