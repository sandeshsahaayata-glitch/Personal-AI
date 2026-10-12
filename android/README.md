# 📱 JARVIS Android Client Setup Guide

Yeh guide aapko JARVIS assistant ko apne Android phone se connect karne aur pura access dene ka aasan tarika batata hai.

---

## ⚡ Method 1: Instant PWA Mode (Recommended - 0 Seconds Setup)

Aapko koi bhi APK compile ya install kiye bina turant 100% same Hacker HUD aur Voice features mil sakte hain:

1. Apne laptop par `start_jarvis.bat` chalayein.
2. Laptop screen par **"📱 PAIR ANDROID"** button dabayein — ek glowing QR code aayega.
3. Apne Android phone ke camera ya browser se QR code scan karein (jaise `http://192.168.x.x:8080`).
4. Phone ke browser me **3 Dots (Menu)** par click karke **"Add to Home Screen" / "Install App"** select karein.
5. **Boom!** JARVIS ka cyber app icon aapke phone ke home screen par aa jayega, jo fullscreen hacker HUD ki tarah open hoga aur microphone & commands support karega.

---

## 🛠️ Method 2: Native Android APK (With Accessibility Service)

Agar aapko WhatsApp auto-typing aur deep background overlay (floating arc bubble) chahiye:

### 1. Requirements:
- Android Studio ya Gradle installed hona chahiye.
- Android phone with Developer Options enabled (USB Debugging / Wireless Debugging).

### 2. Build Steps:
```bash
cd android
./gradlew assembleDebug
```
Output APK yahan milega:
`android/app/build/outputs/apk/debug/app-debug.apk`

### 3. Permissions Enable Karein:
Phone me APK install karne ke baad:
1. **Accessibility Service:** `Settings -> Accessibility -> Installed Apps -> JARVIS` ko ON karein.
   *(Isse WhatsApp me message auto-type aur send ho sakega bina phone root kiye).*
2. **Display over other apps (Overlay):** `Settings -> Apps -> JARVIS -> Appear on top` ko Allow karein.
   *(Isse floating push-to-talk orb screen par kisi bhi app ke upar dikhega).*

---

## 🔒 Security & Anti-Hacking Protection
- **No Open Internet Ports:** JARVIS sirf aapke local Wi-Fi / Hotspot ya Tailscale private mesh VPN par kaam karta hai.
- **HMAC / AES-256 Auth:** Kisi aur device se aane wale unauthorized packets automatically drop ho jate hain.
- **Master PIN Guard:** Shutdown, format, ya sensitive actions ke liye security PIN (Default: `1337`) verify hota hai.
