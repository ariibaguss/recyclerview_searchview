package com.example.barunyoba.ui.skill

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.barunyoba.LanguageAdapter
import com.example.barunyoba.LanguageData
import com.example.barunyoba.R
import com.example.barunyoba.databinding.FragmentSkillBinding
import java.util.ArrayList
import java.util.Locale

class SkillFragment : Fragment() {
    private var _binding: FragmentSkillBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recyclerView = root.findViewById(R.id.recyclerView)
        searchView = root.findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()) // Menggunakan requireContext() untuk mendapatkan konteks fragment
        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : LanguageAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedItem = mList[position]
                val intent = Intent(requireContext(), SkillDetail::class.java)
                intent.putExtra("title", selectedItem.title)
                intent.putExtra("logoResId", selectedItem.logo)
                startActivity(intent)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        return root
    }


    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) { // Membandingkan dengan query dalam huruf kecil
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(LanguageData("Java", R.drawable.java))
        mList.add(LanguageData("Kotlin", R.drawable.kotlin))
        mList.add(LanguageData("C++", R.drawable.cplusplus))
        mList.add(LanguageData("Python", R.drawable.python))
        mList.add(LanguageData("HTML", R.drawable.html))
        mList.add(LanguageData("Swift", R.drawable.swift))
        mList.add(LanguageData("C#", R.drawable.csharp))
        mList.add(LanguageData("JavaScript", R.drawable.javascript))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
