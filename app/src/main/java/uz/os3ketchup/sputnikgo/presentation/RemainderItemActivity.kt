package uz.os3ketchup.sputnikgo.presentation


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.os3ketchup.sputnikgo.R
import uz.os3ketchup.sputnikgo.domain.RemainderItem
import uz.os3ketchup.sputnikgo.domain.RemainderItem.Companion.UNDEFINED_ID
import uz.os3ketchup.sputnikgo.presentation.fragments.RemainderItemFragment

class RemainderItemActivity : AppCompatActivity(), RemainderItemFragment.OnEditingFinishedListener {

    private var screenMode = MODE_UNKNOWN
    private var remainderItemId = UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remainder_item)
        parseIntent()
        if (savedInstanceState == null) {
            launchRightMode()
        }
    }

    private fun launchRightMode() {
        val fragment = when (screenMode) {
            MODE_EDIT -> RemainderItemFragment.newInstanceEditItem(remainderItemId)
            MODE_ADD -> RemainderItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.remainder_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode

        if (screenMode == MODE_EDIT){
            if (!intent.hasExtra(EXTRA_REMAINDER_ITEM_ID)){
                throw RuntimeException("Param remainder  item id is absent ")
            }
            remainderItemId = intent.getIntExtra(EXTRA_REMAINDER_ITEM_ID, UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_REMAINDER_ITEM_ID = "extra_remainder_item_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, RemainderItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, remainderItemId: Int): Intent {
            val intent = Intent(context, RemainderItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_REMAINDER_ITEM_ID, remainderItemId)
            return intent
        }

    }

    override fun onEditingFinished() {
        finish()
    }
}