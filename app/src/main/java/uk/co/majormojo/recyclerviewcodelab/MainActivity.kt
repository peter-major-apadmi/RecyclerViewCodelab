package uk.co.majormojo.recyclerviewcodelab

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import uk.co.majormojo.recyclerviewcodelab.api.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.co.majormojo.recyclerviewcodelab.api.Repo

class MainActivity : AppCompatActivity() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RepoAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycler)
        recycler.setHasFixedSize(true)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = RepoAdapter(::repoClicked)
        recycler.adapter = adapter

        loadItems()
    }

    private fun repoClicked(repo: Repo) {
        Toast.makeText(this, repo.name, Toast.LENGTH_LONG).show()
    }

    private fun loadItems() {
        lifecycleScope.launch(Dispatchers.IO) {
            val service = GithubService.create()
            val response = service.searchRepos("android", 1, 20)

            withContext(Dispatchers.Main) {
                adapter.submitList(response.items)
            }
        }
    }
}

/*
1  Swipe refresh layout
2. Async list differ
3. List adapter
4. Tap on an item
 */


/*


        swipeRefresh = findViewById(R.id.swipe_refresh)
        swipeRefresh.setOnRefreshListener {
            loadItems()
            swipeRefresh.isRefreshing = false
        }




companion object {
    private val diffCallback: DiffUtil.ItemCallback<Repo> =
        object : DiffUtil.ItemCallback<Repo>() {

            override fun areItemsTheSame(
                oldItem: Repo,
                newItem: Repo
            ): Boolean {
                return newItem.id == oldItem.id
            }

            override fun areContentsTheSame(
                oldItem: Repo,
                newItem: Repo
            ): Boolean {
                return newItem == oldItem
            }
        }
}
*/
