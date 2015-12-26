package presenter;
import model.Model;
import view.View;

public interface Presenter {

	
	void setModel(Model model);
	
	void setView(View view);
	
	void printStr(String str);
	
}
