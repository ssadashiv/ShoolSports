package com.example.jclzh.shoolsports.model.adatapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jclzh.shoolsports.R;
import com.example.jclzh.shoolsports.model.bean.StepData;

import java.util.List;

/**
 * Created by lzh on 2018/6/7.
 */

public class HistoricalSportAdatapter extends BaseAdapter {


    List<StepData> list;
    Context context;

    public HistoricalSportAdatapter(List<StepData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lishiwork_item, null);
             viewHolder  = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTvItemToday.setText(list.get(position).getToday());
        viewHolder.mTvItemWork.setText(list.get(position).getStep()+"步");

        return convertView;
    }

    static class ViewHolder {
        View view;
        TextView mTvItemToday;
        TextView mTvItemWork;

        ViewHolder(View view) {
            this.view = view;
            this.mTvItemToday = (TextView) view.findViewById(R.id.tv_item_today);
            this.mTvItemWork = (TextView) view.findViewById(R.id.tv_item_work);
        }
    }
}
