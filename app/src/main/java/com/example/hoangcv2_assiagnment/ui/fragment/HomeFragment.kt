package com.example.hoangcv2_assiagnment.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hoangcv2_assiagnment.*
import com.example.hoangcv2_assiagnment.adapter.CategoryAdapter
import com.example.hoangcv2_assiagnment.adapter.TopProductAdapter
import com.example.hoangcv2_assiagnment.databinding.FragmentHomeBinding
import com.example.hoangcv2_assiagnment.model.Category
import com.example.hoangcv2_assiagnment.viewmodel.ProductViewModel
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject


class HomeFragment : DaggerFragment(), OnItemClickListener {

    private var list: MutableList<Category> = ArrayList<Category>()

    @Inject
    lateinit var viewModel: ProductViewModel

    private lateinit var topProductAdapter: TopProductAdapter

    private lateinit var categoryAdapter: CategoryAdapter

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.setSupportActionBar(binding.toolBarHome)
        //use burger menu
        val toggle = ActionBarDrawerToggle(
            activity, binding.drawerLayout, binding.toolBarHome,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        addDataTopProduct()
        addDataCategoryItem()
        errorResponse()
    }

    private fun addDataTopProduct() {
        binding.recyclerViewTopProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        topProductAdapter = TopProductAdapter(this)
        binding.recyclerViewTopProduct.addItemDecoration(
            RecyclerViewProductMargin(
                2,
                resources.getDimensionPixelSize(R.dimen.recyclerView_topproduct_marginTop)
            )
        )
        viewModel.getProduct()
        viewModel.productList.observe(viewLifecycleOwner, {

            topProductAdapter.getAll(it)
            binding.recyclerViewTopProduct.adapter = topProductAdapter
        })
    }

    private fun addDataCategoryItem() {
        binding.recyclerViewItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = CategoryAdapter(this)
        binding.recyclerViewItem.addItemDecoration(
            RecyclerViewMargin(
                1,
                resources.getDimensionPixelSize(R.dimen.recyclerView_item_marginRight)
            )
        )
        viewModel.getCategory()
        viewModel.categoryList.observe(viewLifecycleOwner, {
            list = it
            categoryAdapter.getAll(list)
            binding.recyclerViewItem.adapter = categoryAdapter
        })
    }

    private fun errorResponse() {
        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_shopping, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
        if (status == Status.CATEGORY) {
            val recyclerFragment = ProductFragment()
            val bundle = Bundle()
            bundle.putString("name", list[position].categoryName)
            recyclerFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)?.replace(R.id.fragment_container, recyclerFragment)?.commit()
        } else if (status == Status.DETAIL) {
            val recyclerFragment = DetailFragment()
            activity?.supportFragmentManager?.beginTransaction()
                ?.addToBackStack(null)?.replace(R.id.fragment_container, recyclerFragment)?.commit()
        }
    }
}