package br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.databinding.DesbravadorItemListBinding
import br.edu.infnet.tp1_desenvolvimentokotlinandroid.models.Desbravador

class DesbravadorAdapter(val listener: DesbravadorListener):
    ListAdapter<
            Desbravador,
            DesbravadorAdapter.ViewHolder
            >(DesbravadorDiffCallback()) {

    override fun onBindViewHolder(holder: DesbravadorAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DesbravadorAdapter.ViewHolder {
        return DesbravadorAdapter.ViewHolder.from(parent, listener)
    }

    class ViewHolder private constructor(
        val binding: DesbravadorItemListBinding,
        val listener: DesbravadorListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Desbravador, position: Int) {
            binding.apply {
                tvNomeDesbravador.text = item.nome
                tvEmail.text = "E-mail: ${item.email}"
                tvIdade.text = "Idade: ${item.idade.toString()}"
                tvUnidade.text = "ID Unidade: ${item.unidadeId.toString()}"

                icDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }

                icEdit.setOnClickListener {
                    listener.onEditClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: DesbravadorListener): DesbravadorAdapter.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DesbravadorItemListBinding.inflate(
                    layoutInflater, parent, false
                )
                return DesbravadorAdapter.ViewHolder(binding, listener)
            }
        }
    }
}

class DesbravadorDiffCallback: DiffUtil.ItemCallback<Desbravador>() {
    override fun areItemsTheSame(oldItem: Desbravador, newItem: Desbravador): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Desbravador, newItem: Desbravador): Boolean {
        return oldItem == newItem
    }
}

// implementar cliques:
// Crie a interface e passe dentro do ViewHolder
interface DesbravadorListener {
    fun onEditClick(desbravador: Desbravador)
    fun onDeleteClick(desbravador: Desbravador)
}

