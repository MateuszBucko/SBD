package app;
import java.util.ArrayList;
import javax.swing.JPanel;
import mapping.Complaint;


public class MainWindow extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {	
		@SuppressWarnings("unused")
		View view = new View();
	
		
		ArrayList<Complaint> list = DatabaseData.getComplaintsBaseOnDecision('Y');
		for(Complaint c : list)
		{
			System.out.println("Decyzja " + c.getComplaintId());
		}
		
		
		
		
	}

}
