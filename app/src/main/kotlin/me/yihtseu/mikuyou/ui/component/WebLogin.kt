package me.yihtseu.mikuyou.ui.component

import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun WebLogin(onSuccess: () -> Unit) {
    val state = rememberWebViewState("https://passport.bilibili.com/h5-app/passport/login")
    val client = object : AccompanistWebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (
                url!!.startsWith("https://passport.bilibili.com/web/sso/exchange_cookie")
                or url.startsWith("https://m.bilibili.com/")
            ) {
                onSuccess()
            }
        }
    }
        WebView(
            state = state,
            modifier = Modifier.fillMaxSize(),
            client = client,
            onCreated = {
                it.settings.javaScriptEnabled = true
                it.settings.builtInZoomControls = true
                it.settings.userAgentString =
                    "Mozilla/5.0 (iPhone13,3; U; CPU iPhone OS 14_0 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) Version/10.0 Mobile/15E148 Safari/602.1"
            })
}