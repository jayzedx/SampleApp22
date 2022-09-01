package com.tutorial.myapplication

import com.tutorial.myapplication.databinding.ListItemCrimeBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.tutorial.myapplication.databinding.ListItemSeriousCrimeBinding
import java.util.*

abstract class CrimeHolder(
    val holderBinding: ViewBinding
) : RecyclerView.ViewHolder(holderBinding.root) {
    abstract fun bind(crime: Crime, onCrimeClicked: (crimeId : UUID) -> Unit)
}


private class NormalCrimeHolder(holderBinding: ListItemCrimeBinding) : CrimeHolder(holderBinding) {
    override fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        val binding = holderBinding as ListItemCrimeBinding
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
            onCrimeClicked(crime.id)
        }
    }
}


private class SeriousCrimeHolder(binding: ListItemSeriousCrimeBinding) : CrimeHolder(binding) {
    override fun bind(crime: Crime, onCrimeClicked: (crimeId: UUID) -> Unit) {
        val binding = holderBinding as ListItemSeriousCrimeBinding
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()
        binding.requiresPolice.text = crime.requiresPolice.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
            onCrimeClicked(crime.id)
        }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>,
    private val onCrimeClicked: (crimeId: UUID) -> Unit = {}
) : RecyclerView.Adapter<CrimeHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CrimeHolder {
        val inflater = LayoutInflater.from(parent.context)


        val holderBinding = when (viewType) {
            R.id.list_item_crime -> ListItemCrimeBinding.inflate(inflater, parent, false)
            else -> ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
        }
        return when(holderBinding) {
            is ListItemCrimeBinding -> NormalCrimeHolder(holderBinding)
            else -> SeriousCrimeHolder(holderBinding as ListItemSeriousCrimeBinding)
        }
    }

    override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
        val crime = crimes[position]
//        holder.apply {
//            binding.crimeTitle.text = crime.title
//            binding.crimeDate.text = crime.date.toString()
//        }
        //The adapter should know as little as possible about the inner
        //workings and details of the view holder
        holder.bind(crime, onCrimeClicked)
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
//        return R.id.list_item_crime
        return when (crimes[position].requiresPolice) {
             true -> R.id.list_item_crime
            else -> R.id.list_item_serious_crime
        }
    }
}
