package com.example.hoangcv2_assiagnment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoangcv2_assiagnment.*
import com.example.hoangcv2_assiagnment.adapter.CartAdapter
import com.example.hoangcv2_assiagnment.adapter.RelatedItemAdapter
import com.example.hoangcv2_assiagnment.databinding.FragmentCartBinding
import com.example.hoangcv2_assiagnment.databinding.FragmentProductBinding
import com.example.hoangcv2_assiagnment.model.Cart
import com.example.hoangcv2_assiagnment.model.Product
import com.example.hoangcv2_assiagnment.viewmodel.ProductViewModel
import dagger.android.support.DaggerFragment
import java.util.ArrayList
import javax.inject.Inject


class CartFragment : DaggerFragment(), OnItemClickListener, View.OnClickListener {

    @Inject
    lateinit var viewModel:ProductViewModel

    private lateinit var binding: FragmentCartBinding

    private lateinit var cartItemAdapter:CartAdapter


    private var id:Int? = null

    private var list: MutableList<Cart> = ArrayList<Cart>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.setSupportActionBar(binding.toolBarCart)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        passData()
        binding.btnAddToCart.setOnClickListener(this)
        addCart()

    }

    private fun passData() {
        val bundle = this.arguments
        if (bundle != null) {
            id = bundle.getInt("id")
        }
        binding.txtCategories.text = id.toString()
    }

    private fun addCart(){
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())
        cartItemAdapter = CartAdapter(this)
        binding.recyclerViewCart.addItemDecoration(
            RecyclerViewCartMargin(
                1,
                resources.getDimensionPixelSize(R.dimen.recyclerView_product_marginTop)
            )
        )
        viewModel.getCart()
        viewModel.cartList.observe(viewLifecycleOwner,{
            list=it
            cartItemAdapter.getAll(list)
            getTotalPrice()
            binding.recyclerViewCart.adapter=cartItemAdapter
        })
    }
    private fun getTotalPrice(){
        var sum:Double =0.0
        for (i in 0 until list.size) {
            sum += list[i].productPrice
        }
        binding.txtPrice.text="$"+"$sum"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(position: Int, status: Status) {
        if(status==Status.DELETE_ITEM_CART){
            list.removeAt(position)
            getTotalPrice()
        }
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.btnAddToCart ->{
                viewModel.deleteCart()
                Toast.makeText(requireContext(),"Your cart has been saved",Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
        }
    }
}