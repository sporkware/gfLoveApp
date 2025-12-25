package com.example.lovecoupons

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder

class FunFragment : Fragment() {

    private lateinit var getVerseButton: Button
    private lateinit var verseText: TextView
    private lateinit var searchEditText: EditText
    private lateinit var searchButton: Button
    private lateinit var songsRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val client = OkHttpClient()
    private val songs = mutableListOf<Song>()
    private lateinit var songAdapter: SongAdapter
    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fun, container, false)

        database = AppDatabase.getDatabase(requireContext())

        getVerseButton = view.findViewById(R.id.getVerseButton)
        verseText = view.findViewById(R.id.verseText)
        searchEditText = view.findViewById(R.id.searchEditText)
        searchButton = view.findViewById(R.id.searchButton)
        songsRecyclerView = view.findViewById(R.id.songsRecyclerView)
        progressBar = view.findViewById(R.id.progressBar)

        songAdapter = SongAdapter(songs) { song ->
            // Open in browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(song.url))
            startActivity(intent)
        }
        songsRecyclerView.layoutManager = LinearLayoutManager(context)
        songsRecyclerView.adapter = songAdapter

        getVerseButton.setOnClickListener {
            fetchRandomVerse()
        }

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                searchSongs(query)
            }
        }

        return view
    }

    private fun fetchRandomVerse() {
        lifecycleScope.launch {
            val cached = database.verseDao().getLatestVerse()
            if (cached != null && System.currentTimeMillis() - cached.timestamp < 60 * 60 * 1000) { // 1 hour
                displayVerse(cached)
                return@launch
            }

            progressBar.visibility = View.VISIBLE
            verseText.text = ""

            val request = Request.Builder()
                .url("https://bible-api.com/?random")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    activity?.runOnUiThread {
                        progressBar.visibility = View.GONE
                        verseText.text = getString(R.string.error)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) {
                            activity?.runOnUiThread {
                                progressBar.visibility = View.GONE
                                verseText.text = getString(R.string.error)
                            }
                            return
                        }

                        val responseBody = response.body?.string()
                        val json = JSONObject(responseBody ?: "{}")
                        val text = json.optString("text", "")
                        val reference = json.optString("reference", "")

                        val verse = CachedVerse(
                            text = text,
                            reference = reference,
                            timestamp = System.currentTimeMillis()
                        )

                        lifecycleScope.launch {
                            database.verseDao().insertVerse(verse)
                        }

                        activity?.runOnUiThread {
                            progressBar.visibility = View.GONE
                            displayVerse(verse)
                        }
                    }
                }
            })
        }
    }

    private fun displayVerse(verse: CachedVerse) {
        verseText.text = "${verse.text}\n\n${verse.reference}"
    }

    private fun searchSongs(query: String) {
        // Open in browser as per description
        val encodedQuery = URLEncoder.encode(query, "UTF-8")
        val url = "https://tabs.ultimate-guitar.com/search.php?search_type=title&value=$encodedQuery"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

data class Song(val title: String, val artist: String, val url: String)

class SongAdapter(
    private val songs: List<Song>,
    private val onItemClick: (Song) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.songTitle)
        val artist: TextView = view.findViewById(R.id.songArtist)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.title.text = song.title
        holder.artist.text = song.artist
        holder.itemView.setOnClickListener { onItemClick(song) }
    }

    override fun getItemCount() = songs.size
}