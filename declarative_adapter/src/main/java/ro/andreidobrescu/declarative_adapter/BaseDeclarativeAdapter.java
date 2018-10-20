package ro.andreidobrescu.declarative_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by adobrescu on 7/11/2017.
 */
public abstract class BaseDeclarativeAdapter extends RecyclerView.Adapter
{
    private Context context;

    public List items;

    public BaseDeclarativeAdapter(Context context)
    {
        super();
        this.context=context;
        this.items=new LinkedList<>();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        try
        {
            Object item=items.get(position);
            ((CellView)holder.itemView).setData(item);
        }
        catch (Exception ex)
        {
            if (BuildConfig.DEBUG)
                ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public List getItems()
    {
        return items;
    }

    public void setItems(List items)
    {
        if (items==null) return;
        this.items = items;
        notifyDataSetChanged();
    }

    public void insertItem(int index, Object item)
    {
        this.items.add(index, item);
        notifyDataSetChanged();
    }

    public void addItems(List items)
    {
        if (items==null) return;
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void clearItems()
    {
        items.clear();
        notifyDataSetChanged();
    }

    public Context getContext()
    {
        return context;
    }
}
