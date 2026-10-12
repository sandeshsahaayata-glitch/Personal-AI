package com.jarvis.assistant

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.os.Bundle
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo

class JarvisAccessibilityService : AccessibilityService() {

    companion object {
        var instance: JarvisAccessibilityService? = null
        var pendingWhatsAppText: String? = null
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return

        // Auto-type & send in WhatsApp
        val packageName = event.packageName?.toString() ?: ""
        if (packageName == "com.whatsapp" && pendingWhatsAppText != null) {
            handleWhatsAppAutomate(pendingWhatsAppText!!)
        }
    }

    override fun onInterrupt() {
        instance = null
    }

    fun handleWhatsAppAutomate(message: String) {
        val rootNode = rootInActiveWindow ?: return
        
        // Find text entry node
        val editTexts = rootNode.findAccessibilityNodeInfosByViewId("com.whatsapp:id/entry")
        if (editTexts.isNotEmpty()) {
            val inputField = editTexts[0]
            val arguments = Bundle().apply {
                putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, message)
            }
            inputField.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments)

            // Allow UI to update, then click send
            android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                val sendButtons = rootNode.findAccessibilityNodeInfosByViewId("com.whatsapp:id/send")
                if (sendButtons.isNotEmpty()) {
                    sendButtons[0].performAction(AccessibilityNodeInfo.ACTION_CLICK)
                    pendingWhatsAppText = null // Reset
                }
            }, 300)
        }
    }

    fun performTap(x: Float, y: Float) {
        val clickPath = Path().apply { moveTo(x, y) }
        val gesture = GestureDescription.Builder()
            .addStroke(GestureDescription.StrokeDescription(clickPath, 0, 50))
            .build()
        dispatchGesture(gesture, null, null)
    }
}
