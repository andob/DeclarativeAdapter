package ro.andreidobrescu.declarative_adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by adobrescu on 7/21/2017.
 */
public abstract class CellView<MODEL> extends RelativeLayout
{
    public CellView(Context context)
    {
        super(context);
        init(getLayout());
    }

    public CellView(Context context, int layout)
    {
        super(context);
        init(layout);
    }

    public CellView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(getLayout());
    }

    public void init(int layout)
    {
        if (layout!=0)
        {
            LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(layout, this, true);
        }
    }

    public int getLayout()
    {
        return 0;
    }

    public abstract void setData(MODEL data);
}
