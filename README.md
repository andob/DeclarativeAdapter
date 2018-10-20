# DeclarativeAdapter

### Easiest way to declare RecyclerView Adapters in Android! No more boilerplate code :)
### If you're using Kotlin, please use [DeclarativeAdapter-kt](https://github.com/andob/DeclarativeAdapter-kt)

### Import

```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
    implementation('com.github.andob:DeclarativeAdapter:1.0.0') {
        exclude group: 'com.android.support'
    }
}
```

### Creating a simple Adapter with one single cell type.

<img src="https://raw.githubusercontent.com/andob/DeclarativeAdapter/master/SimpleDeclarativeAdapter.png" align="left" height="700" >

#### 1. Let's say you have a model class:

```java
public class Restaurant implements Serializable
{
    private String name;
    private String location;
    private int rating;
    private String image;

    //...
}
```

#### 2. Create a Cell View class representing your row / "item view holder" for your model:

You must override two methods:
With ``getLayout`` you specify the layout used for your cell.
With ``setData`` you bind a model object to your views, setup event listeners, etc. Your row will be decoupled from the Activity / Fragment, thus in order to communicate with the activity, you can send events to it (either by using EventBus or by declaring custom event listeners like I did [here](https://github.com/andob/DeclarativeAdapter/blob/master/sample/src/main/java/ro/andreidobrescu/declarativeadapter/restaurant/details/cells/YourCommentCellView.java).

```java
public class RestaurantCellView extends CellView<Restaurant>
{
    @BindView(R.id.cell)
    View cell;

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.nameTv)
    TextView nameTv;
```
```java
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

        cell.setOnClickListener(v ->
        {
            new RestaurantDetailsActivityBundleBuilder()
                    .restaurant(restaurant)
                    .startActivity(v.getContext());
        });

        unbinder.unbind();
    }
}
```

#### 3. Create the layout

Please check that the root element of the layout has height=wrap content, otherwise the row will be full screen.

```java
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
```

#### 4. In your activity / fragment, create the adapter:

```java
SimpleDeclarativeAdapter adapter=new SimpleDeclarativeAdapter<>(this,
            () -> new RestaurantCellView(this));
recyclerView.setAdapter(adapter);
adapter.setItems(provideRestaurants());
```

### Creating a complex Adapter with multiple cell types.

#### 1-3. Create your models and your cells

#### 4. In your activity / fragment, create the adapter:

```java
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
```

<img src="https://raw.githubusercontent.com/andob/DeclarativeAdapter/master/DeclarativeAdapter.png" align="left" width="100%">


The adapter is created in a declarative fashion, using lambda expressions. A list of items will be used by the adapter, containing the restaurant, receipes, comments header and comments. All these model objects will be transformed into cell views (see the picture above).

Define the way the adapter works by using ``whenInstanceof`` (if the model is of this type) and then ``use`` (use this cell view). ``use`` taskes a lambda expression that instantiates the specific cell view. Optionally, you can add extra conditions to the ``whenInstanceof`` rule, by adding an ``and`` rule. With ``and`` you can define virtually any rule by using a lambda expression predicate, based on the index and the model.

``whenInstanceof``, ``and`` and ``use`` must be used only in this order.

All the rules defined must cover all the possible usages (for each element in the adapter's items list, must be at least one rule that can be applied).

### Why DeclarativeAdapter?

1. Single responsibility principle: all the cell view have one and only single role.
2. Decouples your row logic from activities and fragments
3. Never write an adapter class again!
4. No more boring, unmaintainable boilerplate code!
5. Keep all your adapter rules in one concise section of code

### Adapter utility methods

The adapter extends RecyclerView.Adapter and has the following extra utility methods:

1. ``setItems(List items)`` - sets the items and notifies data set changed
2. ``addItems(List items)`` - adds more items to the adapter's list and notified data set changed
3. ``insertItem(int index, Object item)`` - inserts an item in the items list and notifies data set changed
4. ``clearItems()`` - clears the items from the list and notifies data set changed

Furthermore, you have maximum flexibility by directly using adapter's items list:

```java
private void deleteComment(Comment comment)
{
    BaseDeclarativeAdapter adapter=(BaseDeclarativeAdapter) recyclerView.getAdapter();
    adapter.items.remove(comment);
    adapter.notifyDataSetChanged();
}
```

### License

```java
Copyright 2018 Andrei Dobrescu

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.`
```
