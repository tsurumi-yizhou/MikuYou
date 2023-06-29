package me.yihtseu.mikuyou.api

import kotlinx.serialization.Serializable

@Serializable
data class Owner(
    val mid: Long = 0,
    val name: String = "",
    val face: String = ""
)

@Serializable
data class Stat(
    val aid: Long = 0,
    val view: Int = 0,
    val danmaku: Int = 0,
    val reply: Int = 0,
    val favourite: Int = 0,
    val coin: Int = 0,
    val share: Int = 0,
    val like: Int = 0,
)

@Serializable
data class VideoInfo(
    val bvid: String = "",
    val aid: Long = 0,
    val videos: Int = 0,
    val tid: Int = 0,
    val tname: String = "",
    val pic: String = "",
    val title: String = "",
    val ctime: Long = 0,
    val desc: String = "",
    val cid: Long = 0,
    val stat: Stat = Stat(),
    val owner: Owner = Owner()
)

@Serializable
data class Media (
    val id: Int?,
    val baseUrl: String?
)

@Serializable
data class StreamInfo(
    val dash: DashInfo
)

@Serializable
data class DashInfo(
    val video: List<Media>,
    val audio: List<Media>
)

@Serializable
data class Statistic(
    val sid: Long = 0,
    val play: Long = 0,
    val collect: Long = 0,
    val comment: Long = 0,
    val share: Long = 0
)

@Serializable
data class MusicInfo(
    val id: Long = 0,
    val uid: Long = 0,
    val uname: String = "",
    val title: String = "",
    val cover: String = "",
    val intro: String = "",
    val lyric: String = "",
    val duration: Int = 0,
    val passtime: Long = 0,
    val statistic: Statistic = Statistic()
)
