# TODO List for Love App Android Project

1. Project Setup
   - Create new Empty Activity project in Android Studio, Kotlin.
   - App name: Love Coupons or For My Babe ❤️
   - Add dependencies in build.gradle (app):
     - Material, Navigation Fragment/UI KTX, RecyclerView, ConstraintLayout.
     - For network: implementation("com.squareup.okhttp3:okhttp:4.12.0")
     - Or Retrofit + Gson if prefer.
   - Add <uses-permission android:name="android.permission.INTERNET" />

2. UI Structure
   - activity_main.xml: ConstraintLayout with NavHostFragment + BottomNavigationView.
   - menu/bottom_nav_menu.xml: 3 items - Coupons, Dates, Fun (with icons: heart, calendar, smile).
   - nav_graph.xml: 3 fragments.

3. Fragments
   - CouponsFragment:
     - RecyclerView list of hardcoded coupons.
     - Adapter with CardView, text, maybe image.
     - Click: Toast or Dialog "Enjoy your coupon! 💕"

   - DatesFragment:
     - Spinner or Grid of zodiac signs.
     - Button "Get Today's Horoscope"
     - TextViews to display results.
     - Async network call to aztros API (POST).

   - FunFragment:
     - Layout with two sections.
     - Button for random bible: fetch and display.
     - EditText + Button for song search: fetch Songsterr, list results, click open WebView or browser.

4. MainActivity
   - Setup NavController with bottom nav.

5. Polish
   - Theme: romantic colors.
   - Icons drawable.
   - Error handling, loading progress.
   - Test on emulator.

6. Final
   - Build APK.
   - Make sure no crashes.
