package com.zambakcahayrican01.echoesofegypt.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.UIKit.NSLayoutConstraint
import platform.WebKit.WKNavigationAction
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKUIDelegateProtocol
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.WebKit.WKWindowFeatures
import platform.darwin.NSObject

@Suppress("DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")
class PopupManager(
    private val onAddPopup: () -> Unit,
    private val nativeWebView: WKWebView,
) : NSObject(),
    WKUIDelegateProtocol,
    WKNavigationDelegateProtocol {
    private val subWindows: MutableList<WKWebView> = mutableListOf()

    @OptIn(ExperimentalForeignApi::class)
    override fun webView(
        webView: WKWebView,
        createWebViewWithConfiguration: WKWebViewConfiguration,
        forNavigationAction: WKNavigationAction,
        windowFeatures: WKWindowFeatures,
    ): WKWebView? {
        if (forNavigationAction.targetFrame != null && forNavigationAction.targetFrame?.isMainFrame() == true) {
            return null
        }
        val subWebView = WKWebView(CGRectZero.readValue(), createWebViewWithConfiguration)
        subWebView.setUIDelegate(this)
        subWebView.navigationDelegate = this
        nativeWebView.addSubview(subWebView)
        subWebView.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activateConstraints(
            listOf(
                subWebView.topAnchor.constraintEqualToAnchor(nativeWebView.topAnchor),
                subWebView.bottomAnchor.constraintEqualToAnchor(nativeWebView.bottomAnchor),
                subWebView.leadingAnchor.constraintEqualToAnchor(nativeWebView.leadingAnchor),
                subWebView.trailingAnchor.constraintEqualToAnchor(nativeWebView.trailingAnchor),
            ),
        )
        onAddPopup()
        subWindows.add(subWebView)
        subWebView.loadRequest(forNavigationAction.request)
        return subWebView
    }

    fun closeAllPopus() {
        subWindows.forEach { it.removeFromSuperview() }
        subWindows.clear()
    }

    override fun webViewDidClose(webView: WKWebView) {
        closeAllPopus()
        webView.removeFromSuperview()
    }
}
