package com.zambakcahayrican01.echoesofegypt.ui_components

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.util.toUIColor
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewNavigator
import com.multiplatform.webview.web.WebViewState
import com.zambakcahayrican01.echoesofegypt.utils.PopupManager
import platform.Foundation.setValue
import platform.WebKit.javaScriptEnabled

@Composable
fun ContentComponent(
    modifier: Modifier = Modifier,
    onAddPopup: () -> Unit,
    onPopupManagerInit: (PopupManager) -> Unit,
    navigator: WebViewNavigator,
    state: WebViewState,
) {
    WebView(
        modifier = modifier
            .safeDrawingPadding(),
        state = state,
        navigator = navigator,
        onCreated = { view ->
            view.configuration.apply {
                allowsInlineMediaPlayback = true
                defaultWebpagePreferences.allowsContentJavaScript =
                    state.webSettings.isJavaScriptEnabled
                preferences.setValue(
                    state.webSettings.allowFileAccessFromFileURLs,
                    "allowFileAccessFromFileURLs",
                )
                setValue(
                    state.webSettings.allowUniversalAccessFromFileURLs,
                    "allowUniversalAccessFromFileURLs",
                )
                preferences.javaScriptEnabled = state.webSettings.isJavaScriptEnabled
                preferences.javaScriptCanOpenWindowsAutomatically =
                    state.webSettings.isJavaScriptEnabled
            }
            state.webSettings.iOSWebSettings.apply {
                view.scrollView.let {
                    it.bounces = bounces
                    it.scrollEnabled = scrollEnabled
                    it.showsHorizontalScrollIndicator = showHorizontalScrollIndicator
                    it.showsVerticalScrollIndicator = showVerticalScrollIndicator
                }
            }
            state.webSettings.apply {
                val bgColor = iOSWebSettings.backgroundColor ?: backgroundColor
                val scrollViewColor = iOSWebSettings.underPageBackgroundColor ?: backgroundColor
                view.setBackgroundColor(bgColor.toUIColor())
                view.scrollView.setBackgroundColor(scrollViewColor.toUIColor())
                view.scrollView.pinchGestureRecognizer?.enabled = supportZoom
            }
            view.setOpaque(true)
            view.allowsBackForwardNavigationGestures = true
            val popupManager =
                PopupManager(onAddPopup = { onAddPopup() }, nativeWebView = view)
            view.setUIDelegate(popupManager)
            onPopupManagerInit(popupManager)
        }
    )

}
