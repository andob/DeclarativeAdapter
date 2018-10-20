package ro.andreidobrescu.declarative_adapter;

public interface ITypeChecker<MODEL>
{
    boolean check(int index, MODEL model);
}
