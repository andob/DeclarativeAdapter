package ro.andreidobrescu.declarativeadapter.restaurant.details.cells;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.andreidobrescu.declarative_adapter.CellView;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.Receipe;

public class ReceipeCellView extends CellView<Receipe>
{
    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.nameTv)
    TextView nameTv;

    public ReceipeCellView(Context context)
    {
        super(context);
    }

    @Override
    public int getLayout()
    {
        return R.layout.cell_receipe;
    }

    @Override
    public void setData(Receipe receipe)
    {
        Unbinder unbinder=ButterKnife.bind(this);

        Glide.with(getContext())
                .load(receipe.getImage())
                .into(imageView);

        nameTv.setText(receipe.getName());

        unbinder.unbind();
    }
}
