package ro.andreidobrescu.declarative_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adobrescu on 7/11/2017.
 */
public class SimpleDeclarativeAdapter<MODEL> extends BaseDeclarativeAdapter
{
    private IViewCreator<CellView<MODEL>> viewCreator;

    public SimpleDeclarativeAdapter(Context context)
    {
        super(context);
    }

    public SimpleDeclarativeAdapter(Context context, IViewCreator<CellView<MODEL>> viewCreator)
    {
        super(context);
        this.viewCreator=viewCreator;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=this.viewCreator.create();

        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);

        RecyclerView.ViewHolder viewHolder=new RecyclerView.ViewHolder(view)
        {
            @Override
            public String toString()
            {
                return super.toString();
            }
        };

        return viewHolder;
    }
}