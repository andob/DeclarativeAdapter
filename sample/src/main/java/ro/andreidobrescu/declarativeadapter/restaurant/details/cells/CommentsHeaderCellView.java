package ro.andreidobrescu.declarativeadapter.restaurant.details.cells;

import android.content.Context;

import ro.andreidobrescu.declarative_adapter.CellView;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.CommentsHeader;

public class CommentsHeaderCellView extends CellView<CommentsHeader>
{
    public CommentsHeaderCellView(Context context)
    {
        super(context);
    }

    @Override
    public int getLayout()
    {
        return R.layout.cell_comments_header;
    }

    @Override
    public void setData(CommentsHeader data)
    {
    }
}
