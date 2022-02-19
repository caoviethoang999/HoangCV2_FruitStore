package com.example.hoangcv2_assiagnment.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hoangcv2_assiagnment.*
import com.example.hoangcv2_assiagnment.adapter.ProductAdapter
import com.example.hoangcv2_assiagnment.databinding.FragmentProductBinding
import com.example.hoangcv2_assiagnment.model.Cart
import com.example.hoangcv2_assiagnment.model.Category
import com.example.hoangcv2_assiagnment.model.Product
import com.example.hoangcv2_assiagnment.viewmodel.ProductViewModel
import dagger.android.support.DaggerFragment
import java.util.ArrayList
import javax.inject.Inject

class ProductFragment : DaggerFragment(), OnItemClickListener {

    @Inject
    lateinit var viewModel: ProductViewModel

    private lateinit var productAdapter: ProductAdapter

    private var title: String? = null

    private lateinit var binding: FragmentProductBinding

    private var list: MutableList<Product> = ArrayList<Product>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.setSupportActionBar(binding.toolBarProduct)
        appCompatActivity?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        passData()
        addDataVegetables()
        addDataFruits()
    }

    private fun passData() {
        val bundle = this.arguments
        if (bundle != null) {
            title = bundle.getString("name")
        }
        binding.txtCategories.text = title
    }

    private fun addDataVegetables() {
        binding.recyclerViewProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        productAdapter = ProductAdapter(this)
        binding.recyclerViewProduct.addItemDecoration(
            RecyclerViewProductMargin(
                2,
                resources.getDimensionPixelSize(R.dimen.recyclerView_product_marginTop)
            )
        )
        if (title.equals(Constrants.CATEGORY_VEGETABLES)) {
            viewModel.getProductByCategory(1)
            viewModel.productList.observe(viewLifecycleOwner, {
                list=it
                productAdapter.getAll(list)
                binding.recyclerViewProduct.adapter = productAdapter
            })
        }
    }

    private fun addDataFruits() {
        binding.recyclerViewProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        productAdapter = ProductAdapter(this)
        binding.recyclerViewProduct.addItemDecoration(
            RecyclerViewProductMargin(
                2,
                resources.getDimensionPixelSize(R.dimen.recyclerView_product_marginTop)
            )
        )
        if (title.equals(Constrants.CATEGORY_FRUITS)) {
            viewModel.getProductByCategory(2)
            viewModel.productList.observe(viewLifecycleOwner, {
                list=it
                productAdapter.getAll(list)
                binding.recyclerViewProduct.adapter = productAdapter
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_shopping, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            R.id.mnSearch ->{
                val recyclerFragment = CartFragment()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.addToBackStack(null)?.replace(R.id.fragment_container, recyclerFragment)?.commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(position: Int, status: Status) {
        if (status == Status.DETAIL) {
            val recyclerFragment = DetailFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)?.replace(R.id.fragment_container, recyclerFragment)?.commit()
        }else if(status==Status.ADD_TO_CART){
//            val recyclerFragment = CartFragment()
//            val bundle = Bundle()
//            bundle.putInt("id", list[position].productId)
//            recyclerFragment.arguments = bundle
            viewModel.addToCart(list[position].productId)
//            activity?.supportFragmentManager?.beginTransaction()
//                ?.addToBackStack(null)?.replace(R.id.fragment_container, recyclerFragment)?.commit()
            Toast.makeText(requireContext(),"Added to your cart",Toast.LENGTH_SHORT).show()
        }
    }


//    override fun onMenuItemClick(item: MenuItem?): Boolean {
//       when (item!!.itemId){
//            R.id.mnSearch ->{
//                Toast.makeText(requireContext(),"Added to your cart",Toast.LENGTH_SHORT).show()
//                val recyclerFragment = CartFragment()
//                activity?.supportFragmentManager?.beginTransaction()
//                    ?.addToBackStack(null)?.replace(R.id.fragment_container, recyclerFragment)?.commit()
//            }
//        }
//        return true
//    }
}