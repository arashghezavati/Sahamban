package com.example.android.sahamban.Overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.sahamban.Network.SahambanApiFilter
import com.example.android.sahamban.R
import com.example.android.sahamban.databinding.FragmentOverviewBinding


class overviewFragment : Fragment() {

    val viewModel: overviewViewModel by lazy {
        ViewModelProvider(this).get(overviewViewModel::class.java)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {

        val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel=viewModel
        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener{
            viewModel.displayPropertyDetails(it)
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController().navigate(overviewFragmentDirections.actionShowDetail(it))
                viewModel.displayPropertyDetailsCompelete()
            }
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    /**
     * Updates the filter in the [OverviewViewModel] when the menu items are selected from the
     * overflow menu.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_sell_menu -> SahambanApiFilter.SHOW_SELL
                R.id.show_buy_menu -> SahambanApiFilter.SHOW_BUY
                else -> SahambanApiFilter.SHOW_ALL
            }
        )
        return true
    }

    }
