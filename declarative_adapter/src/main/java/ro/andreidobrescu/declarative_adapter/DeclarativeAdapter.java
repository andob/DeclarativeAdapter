package ro.andreidobrescu.declarative_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adobrescu on 7/11/2017.
 */
public class DeclarativeAdapter extends BaseDeclarativeAdapter
{
    private List<CellType> cellTypes;

    public DeclarativeAdapter(Context context)
    {
        super(context);
        this.cellTypes=new ArrayList<>();
        this.setItems(new LinkedList());
    }

    @Override
    public int getItemViewType(int position)
    {
        Object item=getItems().get(position);
        int i=0;
        for(CellType type : cellTypes)
        {
            if (type.check(position, item))
                return i;
            i++;
        }

        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=(View)cellTypes.get(viewType).getViewCreator().create();

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

    public AdapterBuilder addCells()
    {
        return new AdapterBuilder();
    }

    public class AdapterBuilder
    {
        public <MODEL> AdapterBuilder2<MODEL> whenInstanceof(Class<MODEL> clazz)
        {
            return new AdapterBuilder2<MODEL>(clazz, this);
        }

        public DeclarativeAdapter getAdapter()
        {
            return DeclarativeAdapter.this;
        }

        public class AdapterBuilder2<MODEL>
        {
            private AdapterBuilder parent;
            private Class<MODEL> clazz;

            public AdapterBuilder2(Class<MODEL> clazz, AdapterBuilder parent)
            {
                this.clazz=clazz;
                this.parent=parent;
            }

            public AdapterBuilder use(IViewCreator<CellView<MODEL>> viewCreator)
            {
                CellType type=new CellType();
                type.setModelClass(clazz);
                type.setViewCreator(viewCreator);
                cellTypes.add(type);
                return parent;
            }

            public AdapterBuilder3<MODEL> and(ITypeChecker<MODEL> dataTypeCheck)
            {
                return new AdapterBuilder3<MODEL>(dataTypeCheck, clazz, parent);
            }

            public class AdapterBuilder3<MODEL>
            {
                private AdapterBuilder parent;
                private ITypeChecker<MODEL> dataTypeCheck;
                private Class<MODEL> clazz;

                public AdapterBuilder3(ITypeChecker<MODEL> dataTypeCheck, Class<MODEL> clazz, AdapterBuilder parent)
                {
                    this.parent=parent;
                    this.dataTypeCheck=dataTypeCheck;
                    this.clazz=clazz;
                }

                public AdapterBuilder use(IViewCreator<CellView<MODEL>> viewCreator)
                {
                    CellType type=new CellType();
                    type.setModelClass(clazz);
                    type.setTypeChecker(dataTypeCheck);
                    type.setViewCreator(viewCreator);
                    cellTypes.add(type);

                    return parent;
                }
            }
        }
    }
}
