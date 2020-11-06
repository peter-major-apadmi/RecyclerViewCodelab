package uk.co.majormojo.recyclerviewcodelab

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.majormojo.recyclerviewcodelab.api.GithubService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.co.majormojo.recyclerviewcodelab.api.Repo

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = RepoAdapter()
        recycler.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            val service = GithubService.create()
            val response = service.searchRepos("android", 1, 20)

            withContext(Dispatchers.Main) {
                adapter.submitItems(response.items)
            }
        }
    }
}