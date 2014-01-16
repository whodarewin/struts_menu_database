package net.sf.navigator.test;

import java.io.IOException;
import java.sql.SQLException;

import org.xml.sax.SAXException;

import net.sf.navigator.migrate.XmlToDatabase;
import net.sf.navigator.util.LoadableResourceException;

public class MoveTest {
	public static void main(String[] args) throws LoadableResourceException, IOException, SAXException, SQLException {
		XmlToDatabase xml = new XmlToDatabase();
		//xml.move("c:/menu-config.xml");
		String a = "insert into menu ( Description ,AltImage ,Location ,Height ,OnContextMenu ,Onclick ,Module ,Onmouseover ,Align ,Title ,Forward ,Name ,Target ,Onmouseout ,Url ,Action ,Parent_id ,Page ,Image ,Id ,Ondblclick ,Width ,ToolTip ,Roles) values( null ,null ,'?Press' ,null ,null ,'alert('This item has an onclick handler in menu-config.xml.')' ,null ,null ,null ,'Press' ,null ,'Press' ,null ,null ,null ,null ,'1' ,null ,null ,'2' ,null ,null ,null ,null)";
		a = a.replace("'", "\\'");
		System.out.println(a);
	}
}
