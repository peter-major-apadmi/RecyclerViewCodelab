package uk.co.majormojo.recyclerviewcodelab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.majormojo.recyclerviewcodelab.api.Repo

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private var items = listOf<Repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = items[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitItems(items: List<Repo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class RepoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val largeLabel: TextView
        private val smallLabel: TextView

        init {
            largeLabel = view.findViewById(R.id.largeLabel)
            smallLabel = view.findViewById(R.id.smallLabel)
        }

        fun bind(repo: Repo) {
            largeLabel.text = repo.name
            smallLabel.text = repo.description
        }
    }
}
