package lanka.meeting.app.activity

import lanka.meeting.app.R
import lanka.meeting.app.adapteritem.FaqsItem
import lanka.meeting.app.databinding.ActivityFaqsBinding
import lanka.meeting.app.model.Faq
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil

class FaqsActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, FaqsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityFaqsBinding
    private lateinit var faqsAdapter: FastItemAdapter<FaqsItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        val faqItems = getFaqItems()
        setupRecyclerView(faqItems, savedInstanceState)
    }

    override fun onSaveInstanceState(_outState: Bundle) {
        var outState = _outState
        outState = faqsAdapter.saveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun getFaqItems(): ArrayList<FaqsItem> {
        val faqMeetingCode = Faq(
            1,
            getString(R.string.faqs_meeting_code_title),
            getString(R.string.faqs_meeting_code_desc)
        )

        val faqCustomMeetingCode = Faq(
            2,
            getString(R.string.faqs_custom_meeting_code_title),
            getString(R.string.faqs_custom_meeting_code_desc)
        )

        val faqEndMeeting = Faq(
            3,
            getString(R.string.faqs_end_meeting_title),
            getString(R.string.faqs_end_meeting_desc)
        )

        val faqPasswordProtection = Faq(
            4,
            getString(R.string.faqs_password_protection_title),
            getString(R.string.faqs_password_protection_desc)
        )

        val faqs =
            listOf(faqMeetingCode, faqCustomMeetingCode, faqEndMeeting, faqPasswordProtection)

        val faqItems = ArrayList<FaqsItem>()
        for (faq in faqs) {
            faqItems.add(FaqsItem(faq))
        }

        return faqItems
    }

    private fun setupRecyclerView(faqItems: ArrayList<FaqsItem>, savedInstanceState: Bundle?) {
        faqsAdapter = FastItemAdapter()
        faqsAdapter.setHasStableIds(true)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = faqsAdapter
        FastAdapterDiffUtil[faqsAdapter.itemAdapter] = faqItems
        faqsAdapter.withSavedInstanceState(savedInstanceState)
    }
}
