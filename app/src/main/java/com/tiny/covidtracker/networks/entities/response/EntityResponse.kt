import com.google.gson.annotations.SerializedName


data class DataGlobalResponse(
    @SerializedName("updated") val updated: Long,
    @SerializedName("cases") val cases: String,
    @SerializedName("todayCases") val todayCases: String,
    @SerializedName("deaths") val deaths: String,
    @SerializedName("todayDeaths") val todayDeaths: String,
    @SerializedName("recovered") val recovered: String,
    @SerializedName("todayRecovered") val todayRecovered: String,
    @SerializedName("country") val country: String?,
    @SerializedName("countryInfo") val countryInfo: DataCountryResponse?,
)
data class DataCountryResponse(
    @SerializedName("country") val country: String?,
    @SerializedName("iso2") val iso2: String,
    @SerializedName("flag") val flag: String

)
data class DataVNResponse(
    @SerializedName("total") val total: DataVNEntityResponse?,
    @SerializedName("today") val today: DataVNEntityResponse,
    @SerializedName("overview") val overview: List<OverviewEntityResponse>,
    @SerializedName("locations") val locations: List<LocationEntityResponse>

)
data class DataVNEntityResponse(
    @SerializedName("internal") val internal: VNEntityResponse?
)
data class VNEntityResponse(
    @SerializedName("death") val death: String?,
    @SerializedName("treating") val treating: String?,
    @SerializedName("cases") val cases: String?,
    @SerializedName("recovered") val recovered: String?,
)
data class OverviewEntityResponse(
    @SerializedName("date") val date: String?,
    @SerializedName("death") val death: String?,
    @SerializedName("treating") val treating: String?,
    @SerializedName("cases") val cases: String?,
    @SerializedName("recovered") val recovered: String?,
    @SerializedName("avgCases7day") val avgCases7day: String?,
    @SerializedName("avgRecovered7day") val avgRecovered7day: String?,
    @SerializedName("avgDeath7day") val avgDeath7day: String?,
)
data class LocationEntityResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("death") val death: String?,
    @SerializedName("treating") val treating: String?,
    @SerializedName("cases") val cases: String?,
    @SerializedName("recovered") val recovered: String?,
    @SerializedName("casesToday") val casesToday: String?,
)