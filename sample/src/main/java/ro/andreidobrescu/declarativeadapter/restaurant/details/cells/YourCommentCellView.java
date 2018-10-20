package ro.andreidobrescu.declarativeadapter.restaurant.details.cells;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.andreidobrescu.declarative_adapter.CellView;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.Comment;

public class YourCommentCellView extends CellView<Comment>
{
    @BindView(R.id.authorTv)
    TextView authorTv;

    @BindView(R.id.createdAtTv)
    TextView createdAtTv;

    @BindView(R.id.messageTv)
    TextView messageTv;

    @BindView(R.id.deleteButton)
    View deleteButton;

    public interface OnDeleteListener
    {
        void onDelete(Comment comment);
    }

    private OnDeleteListener onDeleteListener;

    public YourCommentCellView(Context context)
    {
        super(context);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener)
    {
        this.onDeleteListener = onDeleteListener;
    }

    @Override
    public int getLayout()
    {
        return R.layout.cell_your_comment;
    }

    @Override
    public void setData(Comment comment)
    {
        Unbinder unbinder= ButterKnife.bind(this);

        authorTv.setText(comment.getAuthor());
        createdAtTv.setText(comment.getCreatedAt());
        messageTv.setText(comment.getMessage());

        deleteButton.setOnClickListener(v ->
        {
            if (onDeleteListener!=null)
                onDeleteListener.onDelete(comment);
        });

        unbinder.unbind();
    }
}
