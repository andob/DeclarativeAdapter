package ro.andreidobrescu.declarative_adapter;

public interface IViewCreator<VIEW extends CellView>
{
    VIEW create();
}
