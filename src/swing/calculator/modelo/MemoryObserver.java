package swing.calculator.modelo;

@FunctionalInterface
public interface MemoryObserver {

	public void changedValue(String newValue);
}
