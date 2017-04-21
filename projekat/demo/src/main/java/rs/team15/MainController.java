package rs.team15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MainController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() throws ClassNotFoundException, SQLException, UnsupportedEncodingException, IOException{
		/*System.out.println("Kreiram demo bazu...");
	    Class.forName("org.h2.Driver");
	    Connection conn = DriverManager.getConnection(
	        "jdbc:h2:mem:myDb:DB_CLOSE_DELAY=-1;MV_STORE=FALSE;MVCC=FALSE;DB_CLOSE_ON_EXIT=FALSE", "sa","");
	    BufferedReader init = new BufferedReader(new InputStreamReader(
	        MainController.class.getResource("projectMRSISA/src/main/java/rs.team15/shema.sql").openStream(), "UTF-8"));
	    ScriptRunner sr = new ScriptRunner(conn, true, true);
	    sr.setDelimiter(";", false);
	    sr.runScript(init);
	    sr.setDelimiter("//", false);
	    conn.close();
	    init.close();*/
		return "index";
	}
}
