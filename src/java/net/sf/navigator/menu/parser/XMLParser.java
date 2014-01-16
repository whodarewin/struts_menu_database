package net.sf.navigator.menu.parser;

import java.io.InputStream;
import java.util.List;

import net.sf.navigator.util.LoadableResourceException;

import org.apache.commons.digester.Digester;

/**
 * get config from xml
 * 
 * @author roger han
 * @date : 2014-1-6 下午03:15:49
 */
public class XMLParser extends Parser {

	@Override
	public void load() throws LoadableResourceException {
		String config = menuRepository.getConfig();
        if (menuRepository.getServletContext() == null) {
            throw new LoadableResourceException("no reference to servlet context found");
        }

        InputStream input = null;
        Digester digester = initDigester();

        try {
            input = menuRepository.getServletContext().getResourceAsStream(config);
            digester.parse(input);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoadableResourceException("Error parsing resource file: " + config + " nested exception is: " + e.getMessage());
        } finally {
            try {
                input.close();
            } catch (Exception e) {
            }
        }
    }


	public Digester initDigester() {
		Digester digester = new Digester();
		digester.setClassLoader(Thread.currentThread().getContextClassLoader());
		digester.push(super.menuRepository);

		// 1 digester.setDebug(getDebug());
		digester.addObjectCreate("MenuConfig/Menus/Menu",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester.addSetProperties("MenuConfig/Menus/Menu");
		digester.addSetNext("MenuConfig/Menus/Menu", "addMenu");

		// 2
		digester.addObjectCreate("MenuConfig/Menus/Menu/Item",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester.addSetProperties("MenuConfig/Menus/Menu/Item");
		digester.addSetNext("MenuConfig/Menus/Menu/Item", "addMenuComponent",
				"net.sf.navigator.menu.MenuComponent");

		// 3
		digester.addObjectCreate("MenuConfig/Menus/Menu/Item/Item",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester.addSetProperties("MenuConfig/Menus/Menu/Item/Item");
		digester.addSetNext("MenuConfig/Menus/Menu/Item/Item",
				"addMenuComponent", "net.sf.navigator.menu.MenuComponent");

		// 4
		digester.addObjectCreate("MenuConfig/Menus/Menu/Item/Item/Item",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester.addSetProperties("MenuConfig/Menus/Menu/Item/Item/Item");
		digester.addSetNext("MenuConfig/Menus/Menu/Item/Item/Item",
				"addMenuComponent", "net.sf.navigator.menu.MenuComponent");

		// 5
		digester.addObjectCreate("MenuConfig/Menus/Menu/Item/Item/Item/Item",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester.addSetProperties("MenuConfig/Menus/Menu/Item/Item/Item/Item");
		digester.addSetNext("MenuConfig/Menus/Menu/Item/Item/Item/Item",
				"addMenuComponent", "net.sf.navigator.menu.MenuComponent");

		// 6
		digester.addObjectCreate(
				"MenuConfig/Menus/Menu/Item/Item/Item/Item/Item",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester
				.addSetProperties("MenuConfig/Menus/Menu/Item/Item/Item/Item/Item");
		digester.addSetNext("MenuConfig/Menus/Menu/Item/Item/Item/Item/Item",
				"addMenuComponent", "net.sf.navigator.menu.MenuComponent");

		// 7
		digester.addObjectCreate(
				"MenuConfig/Menus/Menu/Item/Item/Item/Item/Item/Item",
				"net.sf.navigator.menu.MenuComponent", "type");
		digester
				.addSetProperties("MenuConfig/Menus/Menu/Item/Item/Item/Item/Item/Item");
		digester.addSetNext(
				"MenuConfig/Menus/Menu/Item/Item/Item/Item/Item/Item",
				"addMenuComponent", "net.sf.navigator.menu.MenuComponent");

		digester.addObjectCreate("MenuConfig/Displayers/Displayer",
				"net.sf.navigator.displayer.MenuDisplayerMapping", "mapping");
		digester.addSetProperties("MenuConfig/Displayers/Displayer");
		digester.addSetNext("MenuConfig/Displayers/Displayer",
				"addMenuDisplayerMapping",
				"net.sf.navigator.displayer.MenuDisplayerMapping");
		digester.addSetProperty("MenuConfig/Displayers/Displayer/SetProperty",
				"property", "value");

		return digester;
	}


	@Override
	public void reload() throws LoadableResourceException {
		this.menuRepository.clear();
		load();
		
	}

}
