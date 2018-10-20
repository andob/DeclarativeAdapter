package ro.andreidobrescu.declarativeadapter.restaurant.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.michaelflisar.bundlebuilder.Arg;
import com.michaelflisar.bundlebuilder.BundleBuilder;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ro.andreidobrescu.declarative_adapter.BaseDeclarativeAdapter;
import ro.andreidobrescu.declarative_adapter.DeclarativeAdapter;
import ro.andreidobrescu.declarativeadapter.R;
import ro.andreidobrescu.declarativeadapter.model.Comment;
import ro.andreidobrescu.declarativeadapter.model.CommentsHeader;
import ro.andreidobrescu.declarativeadapter.model.Receipe;
import ro.andreidobrescu.declarativeadapter.model.Restaurant;
import ro.andreidobrescu.declarativeadapter.model.RestaurantDetails;
import ro.andreidobrescu.declarativeadapter.model.User;
import ro.andreidobrescu.declarativeadapter.restaurant.details.cells.CommentCellView;
import ro.andreidobrescu.declarativeadapter.restaurant.details.cells.CommentsHeaderCellView;
import ro.andreidobrescu.declarativeadapter.restaurant.details.cells.ReceipeCellView;
import ro.andreidobrescu.declarativeadapter.restaurant.details.cells.YourCommentCellView;
import ro.andreidobrescu.declarativeadapter.restaurant.list.RestaurantCellView;

@BundleBuilder
public class RestaurantDetailsActivity extends AppCompatActivity
{
    @Arg
    Restaurant restaurant;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);
        RestaurantDetailsActivityBundleBuilder.inject(getIntent().getExtras(), this);
        unbinder=ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DeclarativeAdapter adapter=new DeclarativeAdapter(this);

        adapter.addCells()
                .whenInstanceof(Restaurant.class)
                .use(() -> new RestaurantCellView(this))
                .whenInstanceof(Receipe.class)
                .use(() -> new ReceipeCellView(this))
                .whenInstanceof(CommentsHeader.class)
                .use(() -> new CommentsHeaderCellView(this))
                .whenInstanceof(Comment.class)
                .and((index, comment) -> comment.getCreatedBy()==User.loggedInUserId)
                .use(() ->
                {
                    YourCommentCellView view=new YourCommentCellView(this);
                    view.setOnDeleteListener(this::deleteComment);
                    return view;
                })
                .whenInstanceof(Comment.class)
                .and((index, comment) -> comment.getCreatedBy()!=User.loggedInUserId)
                .use(() -> new CommentCellView(this));

        RestaurantDetails restaurantDetails=provideRestaurantDetails();

        List items=new LinkedList();
        items.add(restaurantDetails.getRestaurant());
        items.addAll(restaurantDetails.getReceipes());

        if (restaurantDetails.getComments().size()>0)
        {
            items.add(new CommentsHeader());
            items.addAll(restaurantDetails.getComments());
        }

        recyclerView.setAdapter(adapter);
        adapter.setItems(items);
    }

    private void deleteComment(Comment comment)
    {
        BaseDeclarativeAdapter adapter=(BaseDeclarativeAdapter) recyclerView.getAdapter();
        adapter.items.remove(comment);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy()
    {
        if (unbinder!=null)
            unbinder.unbind();

        super.onDestroy();
    }

    private RestaurantDetails provideRestaurantDetails()
    {
        RestaurantDetails details=new RestaurantDetails();
        details.setRestaurant(restaurant);

        List<Receipe> receipes=new LinkedList<>();
        receipes.add(new Receipe("Cabage rolls", "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2F1.bp.blogspot.com%2F-EgckJIc_93k%2FVSGWy5_u87I%2FAAAAAAAAJh4%2FzfEKqiqN_iM%2Fs1600%2FCabbage%252BRolls-001-2.jpg&f=1"));
        receipes.add(new Receipe("Pizza", "https://proxy.duckduckgo.com/iu/?u=http%3A%2F%2Fincrediblemos.com%2Fwp-content%2Fuploads%2F2013%2F01%2Fpizza-slice.jpg&f=1"));
        receipes.add(new Receipe("Pasta", "https://proxy.duckduckgo.com/iur/?f=1&image_host=http%3A%2F%2Fwww.seriouseats.com%2Frecipes%2Fassets_c%2F2016%2F08%2F20160827-cherry-tomato-pasta-13-thumb-1500xauto-433876.jpg&u=https://www.seriouseats.com/recipes/assets_c/2016/08/20160827-cherry-tomato-pasta-13-thumb-1500xauto-433876.jpg"));
        details.setReceipes(receipes);

        List<Comment> comments=new LinkedList<>();
        comments.add(new Comment("Great!", "Andrei", "20.10.2018 14:30", User.loggedInUserId));
        comments.add(new Comment("Comment 2", "Another user", "20.10.2018 14:20", 2));
        comments.add(new Comment("Comment 3", "Another user", "20.10.2018 14:10", 2));
        details.setComments(comments);

        return details;
    }
}
