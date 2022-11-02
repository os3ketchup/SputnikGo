package uz.os3ketchup.sputnikgo.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.os3ketchup.sputnikgo.R
import uz.os3ketchup.sputnikgo.presentation.RemainderItemActivity.Companion.newIntentAddItem
import uz.os3ketchup.sputnikgo.presentation.RemainderItemActivity.Companion.newIntentEditItem
import uz.os3ketchup.sputnikgo.presentation.diff.RemainderListAdapter
import uz.os3ketchup.sputnikgo.presentation.diff.RemainderListAdapter.Companion.MAX_POOL_SIZE
import uz.os3ketchup.sputnikgo.presentation.fragments.RemainderItemFragment
import uz.os3ketchup.sputnikgo.presentation.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(),RemainderItemFragment.OnEditingFinishedListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var remainderListAdapter: RemainderListAdapter
    private var remainderItemContainer: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        hideStatusBar()

        remainderItemContainer = findViewById(R.id.remainder_item_container)

        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.remainderList.observe(this) {
            remainderListAdapter.submitList(it)
        }

        val buttonShopItem = findViewById<FloatingActionButton>(R.id.button_add_remainder_item)

        buttonShopItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(RemainderItemFragment.newInstanceAddItem())
            }
        }

    }

    private fun setupRecyclerView() {
        val rvRemainderList = findViewById<RecyclerView>(R.id.rv_remainder_list)
        with(rvRemainderList) {
            remainderListAdapter = RemainderListAdapter()
            adapter = remainderListAdapter
            recycledViewPool.setMaxRecycledViews(
                R.layout.item_remainder_enabled, MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                R.layout.item_remainder_disabled, MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvRemainderList)
    }

    private fun setupSwipeListener(rvRemainderList: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = remainderListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvRemainderList)
    }

    private fun setupClickListener() {
        remainderListAdapter.onRemainderItemClickListener = {
            if (isOnePaneMode()) {
                val intent = newIntentEditItem(this,it.id)
                startActivity(intent)
            } else {
                launchFragment(RemainderItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.remainder_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun isOnePaneMode(): Boolean {
        return remainderItemContainer == null
    }

    private fun setupLongClickListener() {
        remainderListAdapter.onRemainderItemLongClickListener = {
            viewModel.changeEnabledState(it)
        }
    }

    private fun hideStatusBar() {
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
    }

    override fun onEditingFinished() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        supportFragmentManager.popBackStack()
    }

}