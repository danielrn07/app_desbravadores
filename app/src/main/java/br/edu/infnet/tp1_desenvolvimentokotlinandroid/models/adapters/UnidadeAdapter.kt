package br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.daos.UnidadeDao
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.UnidadeItemListBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Unidade

class UnidadeAdapter(val listener: UnidadeListener):
    ListAdapter<
            Unidade,
            UnidadeAdapter.ViewHolder
            >(UnidadeDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    class ViewHolder private constructor(
        val binding: UnidadeItemListBinding,
        val listener: UnidadeListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Unidade, position: Int) {
            binding.apply {
                tvNomeUnidade.text = item.nome

                icDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }

                icEdit.setOnClickListener {
                    listener.onEditClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: UnidadeListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UnidadeItemListBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }
}

class UnidadeDiffCallback: DiffUtil.ItemCallback<Unidade>() {
    override fun areItemsTheSame(oldItem: Unidade, newItem: Unidade): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Unidade, newItem: Unidade): Boolean {
        return oldItem == newItem
    }
}

// implementar cliques:
// Crie a interface e passe dentro do ViewHolder
interface UnidadeListener {
    fun onEditClick(unidade: Unidade)
    fun onDeleteClick(unidade: Unidade)
}










