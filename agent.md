You are an expert Android developer using Kotlin and Android Studio (latest version).

Your task is to build a complete Android app called "Love Coupons" (or something romantic for my girlfriend).

App features:
- Opens with a nice icon (heart or something cute) and splash screen optional.
- Bottom navigation with 3 tabs: Coupons, Dates, Fun.
- Make it pretty with Material Design, cards, colors (pink/red theme maybe), big title like "For My Love ❤️ [Her Name]".

Sections:

1. Coupons:
   - List of redeemable coupons, hardcoded.
   - Use RecyclerView with CardView items. Make cards look romantic: pink/red background, heart icons, rounded corners, maybe emoji in text.
   - Hardcode at least 15-20 fun personalized coupons.
   - Examples to include (feel free to add more similar):
     - "1/2 Hour Back Massage" 
     - "Unlimited Cuddling Session"
     - "Breakfast in Bed"
     - "Movie Night - You Choose the Film"
     - "Foot Rub Anytime"
     - "Hug Attack Whenever You Want"
     - "No Dishes Night – I Handle Cleanup"
     - "Personal Playlist – Songs That Remind Me of You 🎶"
     - "Adventure Date – You Pick, I Plan"
     - "Compliment Shower – 10 Minutes of Why You're Amazing 😘"
     - "Cook Your Favorite Meal from Scratch 🍲"
     - "Phone-Free Hour – Just Us Talking/Cuddling 📵"
     - "Wild Card Wish – Anything Reasonable You Want ✨"
     - "Memory Lane – Look at Old Pics & Share Stories 📸"
     - "Dance Party in the Living Room 💃🕺"
     - "Guilt-Free Nap – I Handle Everything 😴"
     - "Handwritten Love Letter 💌"
     - "Stargazing Night Under Blanket ⭐"

     - Make them clickable, show a dialog "Redeemed! Enjoy your treat 😘💕" or nice animation/confetti.

2. Dates:
   - Daily horoscope section.
   - User selects zodiac sign (dropdown or buttons for 12 signs).
   - Button to fetch today's horoscope.
   - Use free API: https://aztro.sameerkumar.website/?sign=aries&day=today (POST request, no key needed).
   - Signs: lowercase like aries, taurus, etc.
   - Display: date, description, mood, color, lucky number, lucky time, compatibility.
   - Use Retrofit or OkHttp for network call.
   - Show loading, error if no internet.

3. Fun:
   - Two parts:
     - Random Bible Verse: Button "Get Random Verse".
       - Use free API: https://bible-api.com/?random (GET, returns JSON with verse text and reference).
       - Or fallback: https://labs.bible.org/api/?passage=random&type=json
       - Display verse and reference nicely.
     - Song Tabs Search:
       - EditText for search song/artist.
       - Button search.
       - Use Songsterr search API: https://www.songsterr.com/a/wa/bestMatchForQueryString?s=QUERY (replace QUERY with url encoded search).
       - Get list of results, show in RecyclerView: title, artist.
       - Click item: get song ID, open in WebView the player page https://www.songsterr.com/a/wa/song?id=ID
       - Or simple: on search, open browser to https://tabs.ultimate-guitar.com/search.php?search_type=title&value=QUERY

General:
- Use Jetpack Navigation for bottom nav and fragments.
- Add dependencies: material, navigation, recyclerview, retrofit/okhttp/gson, etc.
- Internet permission in manifest.
- Handle offline gracefully.
- Make it simple, no login, just for her phone.
- Add some hearts/emojis, make it romantic.

Output full code files when done, or step by step.
Start with project setup, then main activity, nav, then one fragment at a time.

