package ro.andreidobrescu.declarativeadapter.restaurant.details.cells;

import android.content.Context;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.andreidobrescu.declarative_adapter.CellView;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.Comment;

public class CommentCellView extends CellView<Comment>
{
    @BindView(R.id.authorTv)
    TextView authorTv;

    @BindView(R.id.createdAtTv)
    TextView createdAtTv;

    @BindView(R.id.messageTv)
    TextView messageTv;

    public CommentCellView(Context context)
    {
        super(context);
    }

    @Override
    public int getLayout()
    {
        return R.layout.cell_comment;
    }

    @Override
    public void setData(Comment comment)
    {
        Unbinder unbinder=ButterKnife.bind(this);

        authorTv.setText(comment.getAuthor());
        createdAtTv.setText(comment.getCreatedAt());
        messageTv.setText(comment.getMessage());

        unbinder.unbind();
    }
}
