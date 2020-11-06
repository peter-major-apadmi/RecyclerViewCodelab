package uk.co.majormojo.recyclerviewcodelab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uk.co.majormojo.recyclerviewcodelab.api.Repo

typealias RepoClickHandler = (Repo) -> Unit

class RepoAdapter(private val clickHandler: RepoClickHandler) : ListAdapter<Repo, RepoAdapter.RepoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = getItem(position)
        holder.bind(repo)
    }

    inner class RepoViewHolder(view: View): RecyclerView.ViewHolder(view) {

        init {
            itemView.setOnClickListener {
                clickHandler(repo)
            }
        }

        private lateinit var repo: Repo
        private val largeLabel: TextView = view.findViewById(R.id.largeLabel)
        private val smallLabel: TextView = view.findViewById(R.id.smallLabel)

        fun bind(repo: Repo) {
            this.repo = repo
            largeLabel.text = repo.name
            smallLabel.text = repo.description
        }
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
}
