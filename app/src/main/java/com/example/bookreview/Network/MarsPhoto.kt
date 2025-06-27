import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenLibrarySearchResponse(
    @SerialName("numFound") val numFound: Int,
    @SerialName("start") val start: Int,
    @SerialName("docs") val docs: List<BookDoc>
)
@Serializable
data class BookDoc(
    @SerialName("key") val key: String,
    @SerialName("title") val title: String? = null,
    @SerialName("author_name") val authorName: List<String>? = null,
    @SerialName("cover_i") val coverId: Long? = null,
    @SerialName("isbn") val isbn: List<String>? = null,
    @SerialName("first_publish_year") val firstPublishYear: Int? = null
) {
    fun getCoverUrl(size: String = "M"): String? {
        return coverId?.let {
            "https://covers.openlibrary.org/b/id/$it-$size.jpg"
        }
    }
    fun getFormattedAuthors(): String {
        return authorName?.joinToString(",") ?: "Unknown Author"
    }
}
