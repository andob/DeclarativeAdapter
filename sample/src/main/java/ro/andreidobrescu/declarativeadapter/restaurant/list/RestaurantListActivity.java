package ro.andreidobrescu.declarativeadapter.restaurant.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.andreidobrescu.declarative_adapter.SimpleDeclarativeAdapter;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.Restaurant;

public class RestaurantListActivity extends AppCompatActivity
{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        unbinder=ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SimpleDeclarativeAdapter adapter=new SimpleDeclarativeAdapter<>(this,
            () -> new RestaurantCellView(this));
        recyclerView.setAdapter(adapter);
        adapter.setItems(provideRestaurants());
    }

    @Override
    protected void onDestroy()
    {
        if (unbinder!=null)
            unbinder.unbind();

        super.onDestroy();
    }

    private List<Restaurant> provideRestaurants()
    {
        List<Restaurant> restaurants=new LinkedList<>();
        restaurants.add(new Restaurant("Caru cu Bere", "Bucharest", 5, "https://www.alux.com/wp-content/uploads/2014/08/The-Best-Restaurants-In-New-York-City-Le-Bernardin1.jpg"));
        restaurants.add(new Restaurant("Casa Boema", "Cluj", 4, "https://www.alux.com/wp-content/uploads/2014/08/The-Best-Restaurants-In-New-York-City-Le-Bernardin1.jpg"));
        restaurants.add(new Restaurant("Menza", "Budapest", 3, "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fwww.traveloutthere.com%2Ffiles%2Fphoto_gallery%2F457x306%2Fbudapest-liszt-01.jpg&f=1"));
        restaurants.add(new Restaurant("Yakiniku", "Tokyo", 5, "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fjapan-tourist-guide.com%2Fhorumon-yakiniku.jpg&f=1"));
        restaurants.add(new Restaurant("Le Bernadin", "New York", 5, "https://www.alux.com/wp-content/uploads/2014/08/The-Best-Restaurants-In-New-York-City-Le-Bernardin1.jpg"));
        return restaurants;
    }
}
