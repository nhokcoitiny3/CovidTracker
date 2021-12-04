import com.google.gson.annotations.SerializedName


data class DataTotalResponse(
    @SerializedName("ID") val id: String,
    @SerializedName("Message") val message: String,
    @SerializedName("Global") val global: DataEntityResponse,
    @SerializedName("Countries") val countries: List<DataEntityResponse>,
    @SerializedName("Date") val date: String
)
data class DataEntityResponse(
    @SerializedName("ID") val ID: String,
    @SerializedName("Country") val country: String,
    @SerializedName("CountryCode") val countryCode: String,
    @SerializedName("Slug") val slug: String,
    @SerializedName("NewConfirmed") val newConfirmed: String,
    @SerializedName("TotalConfirmed") val totalConfirmed: String,
    @SerializedName("NewDeaths") val newDeaths: String,
    @SerializedName("TotalDeaths") val totalDeaths: String,
    @SerializedName("NewRecovered") val newRecovered: String,
    @SerializedName("TotalRecovered") val totalRecovered: String,
    @SerializedName("Date") val date: String
)