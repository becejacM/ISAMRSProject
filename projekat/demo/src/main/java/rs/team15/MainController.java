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
		return "index";
	}
}
