package taskboard.control;

import java.sql.SQLException;
import taskboard.dao.StudenteDAO;

public class LoginControl {
	
	private StudenteDAO studenteDAO;
	
	public LoginControl() {
		studenteDAO = new StudenteDAO();
	}
	
	public boolean login(String matricola, String password) throws SQLException{
		return studenteDAO.autentica(matricola, password);
	}

}
