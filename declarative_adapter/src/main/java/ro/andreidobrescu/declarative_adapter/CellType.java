package ro.andreidobrescu.declarative_adapter;

/**
 * Created by adobrescu on 7/11/2017.
 */
public class CellType<MODEL>
{
    private IViewCreator<CellView<MODEL>> viewCreator;
    private ITypeChecker<MODEL> typeChecker;
    private Class<MODEL> modelClass;

    public IViewCreator<CellView<MODEL>> getViewCreator()
    {
        return viewCreator;
    }

    public void setViewCreator(IViewCreator<CellView<MODEL>> viewCreator)
    {
        this.viewCreator=viewCreator;
    }

    public ITypeChecker<MODEL> getTypeChecker()
    {
        return typeChecker;
    }

    public void setTypeChecker(ITypeChecker<MODEL> typeChecker)
    {
        this.typeChecker=typeChecker;
    }

    public void setModelClass(Class<MODEL> modelClass)
    {
        this.modelClass=modelClass;
    }

    public Class<MODEL> getModelClass()
    {
        return modelClass;
    }

    public boolean check(int index, Object item)
    {
        try
        {
            if (item.getClass().equals(modelClass))
            {
                if (typeChecker==null)
                {
                    return true;
                }
                else
                {
                    return typeChecker.check(index, (MODEL)item);
                }
            }

            return false;
        }
        catch (Exception ex) {}

        return false;
    }
}
