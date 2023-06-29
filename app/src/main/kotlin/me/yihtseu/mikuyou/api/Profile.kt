package me.yihtseu.mikuyou.api

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val mid: Long = 0,
    val name: String = "",
    val face: String = "",
    val level: Int = 0,
    val sign: String = "",
    val is_followed: Boolean = false
)

@Serializable
data class KeyInfo(
    val hash: String = "",
    val key: String = ""
)

@Serializable
data class LoginInfo(
    val isLogin: Boolean = false,
    val face: String = "",
    val mid: Long = 0,
    val money: Int = 0,
    val uname: String = ""
)

@Serializable
data class Geetest(
    val gt: String = "",
    val challenge: String = ""
)

@Serializable
data class CaptchaInfo(
    val geetest: Geetest = Geetest(),
    val token: String = ""
)