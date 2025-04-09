package com.zambakcahayrican01.echoesofegypt.utils

import com.multiplatform.webview.request.RequestInterceptor
import com.multiplatform.webview.request.WebRequest
import com.multiplatform.webview.request.WebRequestInterceptResult
import com.multiplatform.webview.web.WebViewNavigator
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class ContentInterceptor : RequestInterceptor {
    override fun onInterceptUrlRequest(
        request: WebRequest,
        navigator: WebViewNavigator
    ): WebRequestInterceptResult {
        val url = request.url
        return if (url.startsWith("tel:") ||
            url.startsWith("mailto:") ||
            url.startsWith("maps:") ||
            url.startsWith("t.me/") ||
            url.startsWith("tg")
        ) {
            UIApplication.sharedApplication.openURL(NSURL(string = url))
            WebRequestInterceptResult.Reject
        } else {
            WebRequestInterceptResult.Allow
        }
    }
}