package me.yihtseu.mikuyou.api

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val cid: Long = 0,
    val part: String = "",
    val duration: Long = 0,
    val first_frame: String = ""
)

@Serializable
data class PopularInfo(
    val list: List<VideoInfo> = listOf(),
    val no_more: Boolean = false
)

@Serializable
data class WbiImg(
    val img_url: String = "",
    val sub_url: String = ""
)

@Serializable
data class NavInfo(
    val isLogin: Boolean = false,
    val face: String = "",
    val mid: Long = 0,
    val money: Double = 0.0,
    val wbi_img: WbiImg = WbiImg()
)

@Serializable
data class Page(
    val num: Int = 1,
    val size: Int = 20,
    val count: Long = 0
)

@Serializable
data class RegionInfo(
    val page: Page = Page(),
    val archives: List<VideoInfo> = listOf()
)