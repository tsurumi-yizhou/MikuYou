package me.yihtseu.mikuyou.api

import android.webkit.CookieManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Serializable
data class Response<T>(
    val code: Int?, val message: String?, val ttl: Int? = 0, val data: T?
)


class BilibiliClient(Queue: RequestQueue) {
    private val json = Json { ignoreUnknownKeys = true }
    private val queue = Queue

    private inline fun <reified T> parseResponse(raw: String): T {
            val response = json.decodeFromString<Response<T>>(raw)
            if (response.code == 0) return response.data!!
            else throw Exception(response.message)
    }

    private suspend inline fun <reified T> get(url: String) = suspendCoroutine<T> { continuation ->
        val request = object : StringRequest(Method.GET, url, {
                val result = parseResponse<T>(it)
                continuation.resume(result)
        }, { throw it }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = super.getHeaders().toMutableMap()
                headers.put("Cookie", CookieManager.getInstance().getCookie("api.bilibili.com"))
                headers.put(
                    "User-Agent",
                    "Mozilla/5.0 (iPhone13,3; U; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/15E148 Safari/602.1"
                )
                return headers
            }
        }
        queue.add(request)
    }

    private suspend inline fun <reified T> post(
        url: String,
        headers: MutableMap<String, String>,
        params: MutableMap<String, String>
    ) = suspendCoroutine<T> { continuation ->
        val request = object : StringRequest(Method.POST, url, {
            continuation.resume(parseResponse<T>(it))
        }, { throw it }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = super.getHeaders().toMutableMap()
                headers.put("Cookie", CookieManager.getInstance().getCookie("bilibili.com"))
                headers.put(
                    "User-Agent",
                    "Mozilla/5.0 (iPhone13,3; U; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/15E148 Safari/602.1"
                )
                return headers
            }

            override fun getBody(): ByteArray {
                return params.map { it.key + "=" + it.value }.joinToString("&").toByteArray()
            }
        }
    }

    suspend fun getVideoInfo(bvid: String): VideoInfo = get("https://api.bilibili.com/x/web-interface/view?bvid=$bvid")

    suspend fun getStreamInfo(
        bvid: String, cid: Long
    ): StreamInfo = get("https://api.bilibili.com/x/player/playurl?bvid=$bvid&cid=$cid&fnval=16")

    suspend fun getMusicInfo(sid: Long): MusicInfo =
        get("https://www.bilibili.com/audio/music-service-c/web/song/info?sid=$sid")

    suspend fun getPopularInfo(pn: Int = 1, ps: Int = 20): PopularInfo =
        get("https://api.bilibili.com/x/web-interface/popular?pn=$pn&ps=$ps")

    suspend fun getPageInfo(bvid: String): List<PageInfo> =
        get<List<PageInfo>>("https://api.bilibili.com/x/player/pagelist?bvid=$bvid")

    suspend fun getRecommended(bvid: String): List<VideoInfo> =
        get<List<VideoInfo>>("https://api.bilibili.com/x/web-interface/archive/related?bvid=$bvid")

    suspend fun getRegion(tid: Int, pn: Int = 1, ps: Int = 20): RegionInfo =
        get<RegionInfo>("https://api.bilibili.com/x/web-interface/dynamic/region?pn=$pn&ps=$ps&rid=$tid")

    suspend fun checkLogin(): LoginInfo = get<LoginInfo>("https://api.bilibili.com/x/web-interface/nav")
}