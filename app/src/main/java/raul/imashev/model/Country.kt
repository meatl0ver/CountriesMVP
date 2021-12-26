package raul.imashev.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country(
    @Expose
    val name: Name? = null,
    @Expose
    val flags: Flags? = null,
    @Expose
    val area: Double? = null,
    @Expose
    val capital: List<String>? = null,
    @Expose
    val continents: List<String>? = null,
    @Expose
    val population: Int? = null,
    @Expose
    val region: String? = null,
    @Expose
    val fifa: String? = null,
) : Parcelable

@Parcelize
data class Name(
    @Expose
    val common: String? = null,
    @Expose
    val official: String? = null
) : Parcelable

@Parcelize
data class Flags(
    @Expose
    val png: String? = null
) : Parcelable
