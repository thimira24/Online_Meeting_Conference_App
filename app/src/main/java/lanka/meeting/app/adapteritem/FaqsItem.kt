package lanka.meeting.app.adapteritem

import lanka.meeting.app.R
import lanka.meeting.app.databinding.ItemFaqsBinding
import lanka.meeting.app.model.Faq
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class FaqsItem(val faq: Faq) :
    AbstractBindingItem<ItemFaqsBinding>() {

    override val type: Int
        get() = R.id.fastadapter_faqs_item_id

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ItemFaqsBinding {
        return ItemFaqsBinding.inflate(inflater, parent, false)
    }

    override fun bindView(binding: ItemFaqsBinding, payloads: List<Any>) {
        with(binding) {
            tvTitle.text = faq.title
            tvDescription.text = faq.description
        }

    }

    override fun unbindView(binding: ItemFaqsBinding) {
        with(binding) {
            tvTitle.text = null
            tvDescription.text = null
        }
    }


}