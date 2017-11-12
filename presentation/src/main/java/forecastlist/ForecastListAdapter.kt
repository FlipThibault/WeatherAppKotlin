package forecastlist

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import base.DataProvider
import base.Identifiable
import base.BasicListInfoCellVIew
import base.BasicListInfoCellViewModel
import base.BasicListHeaderCellVIew
import base.BasicListHeaderCellViewModel

/**
 * Created by pthibau1 on 2017-10-17.
 *
 * The adapter should coordinate the events from the recycler view. It should also know
 * which view to inflate for specific cell type. However it delegates all logic to a
 * specific "RecyclerAdapterDelegate" which is in charge of any logic. The specific view bindings
 * are done within the view holders; therefore, our adapter remains quite lean.
 */
class ForecastListAdapter(val dataProvider: DataProvider<ForecastListViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            ForecastListViewModel.HEADER_CELL_VIEW_TYPE -> {
                return ForecastListHeaderViewHolder(BasicListHeaderCellVIew(parent?.context))
            }

            ForecastListViewModel.STANDARD_CELL_VIEW_TYPE -> {
                return ForecastListItemViewHolder(BasicListInfoCellVIew(parent?.context))
            }

            else -> {
                throw Exception("ForecastListAdapter does not support the view type sent")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewType = getItemViewType(position)
        when (viewType) {
            ForecastListViewModel.HEADER_CELL_VIEW_TYPE -> {
                (holder as? ForecastListHeaderViewHolder)?.let { forecastListHeaderViewHolder ->
                    forecastListHeaderViewHolder.bindData(dataProvider.getData().getItem(position) as BasicListHeaderCellViewModel)
                }
            }

            ForecastListViewModel.STANDARD_CELL_VIEW_TYPE -> {
                (holder as? ForecastListItemViewHolder)?.let { forecastItemViewHolder ->
                    forecastItemViewHolder.bindData(dataProvider.getData().getItem(position) as BasicListInfoCellViewModel)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataProvider.getData().getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return dataProvider.getData().getItemCount()
    }

    override fun getItemId(position: Int): Long {
        (dataProvider.getData().getItem(position) as? Identifiable<String>)?.let { idenfitifableItem ->
            return idenfitifableItem.getIdentifier().hashCode().toLong()
        }
        throw ClassCastException("Your cell item (data) must implement Identifiable")
    }

}