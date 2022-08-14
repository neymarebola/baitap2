package com.example.btvn2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.btvn2.R
import com.example.btvn2.adapters.ItemAdapter
import com.example.btvn2.api_service.JsonPlaceHolderApi
import com.example.btvn2.models.Item
import com.example.btvn2.models.Root
import com.example.btvn2.utils.Credentials
import kotlinx.android.synthetic.main.fragment_choban.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChobanFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChobanFragment : Fragment() {
    private var newFeedApi: JsonPlaceHolderApi? = null
    private var listItems = mutableListOf<Item>()
    private var adapter: ItemAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newFeedApi = retrofit.create(JsonPlaceHolderApi::class.java)
        getNewFeed()
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        recyclerView!!.layoutManager = layoutManager
//        adapter = ItemAdapter(requireContext(), listItems!!)
//        recyclerView!!.adapter = adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choban, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.choban_fragment_rec)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView!!.layoutManager = layoutManager
        adapter = ItemAdapter(requireContext(), listItems!!)
        recyclerView!!.adapter = adapter
    }

    fun getNewFeed() {
        val call: Call<Root> = newFeedApi!!.getListNewFeed()
        call.enqueue(object : Callback<Root?> {
            override fun onResponse(call: Call<Root?>, response: Response<Root?>) {
                if (!response.isSuccessful()) {
                    Log.d("tag", response.code().toString())
                    return
                }
                val root: Root? = response.body()
                if (listItems != null) {
                    listItems!!.clear()
                    //listItems!!.addAll(root?.items!!)
                    adapter?.addItem(root?.items!!)
                }

            }

            override fun onFailure(call: Call<Root?>, t: Throwable) {
                Log.d("tag", t.message!!)
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChobanFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChobanFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}