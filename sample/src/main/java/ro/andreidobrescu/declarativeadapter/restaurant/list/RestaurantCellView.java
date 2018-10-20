package ro.andreidobrescu.declarativeadapter.restaurant.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.andreidobrescu.declarative_adapter.CellView;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.Restaurant;
import ro.andreidobrescu.declarativeadapter.restaurant.details.RestaurantDetailsActivityBundleBuilder;

public class RestaurantCellView extends CellView<Restaurant>
{
    @BindView(R.id.cell)
    View cell;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.nameTv)
    TextView nameTv;

    @BindView(R.id.locationTv)
    TextView locationTv;

    @BindView(R.id.ratingTv)
    TextView ratingTv;

    public RestaurantCellView(Context context)
    {
        super(context);
    }

    @Override
    public int getLayout()
    {
        return R.layout.cell_restaurant;
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void setData(Restaurant restaurant)
    {
        Unbinder unbinder=ButterKnife.bind(this);

        Glide.with(getContext())
                .load(restaurant.getImage())
                .into(imageView);

        nameTv.setText(restaurant.getName());
        locationTv.setText(restaurant.getLocation());
        ratingTv.setText("Rating: "+restaurant.getRating()+"/5");

        cell.setOnClickListener(v ->
        {
            new RestaurantDetailsActivityBundleBuilder()
                    .restaurant(restaurant)
                    .startActivity(v.getContext());
        });

        unbinder.unbind();
    }
}
