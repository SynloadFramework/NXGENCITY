package us.nxgencity.controls.elements;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import us.nxgencity.map.sql.Entry;

import com.synload.framework.handlers.Request;
import com.synload.framework.handlers.Response;
import com.synload.framework.ws.WSHandler;

public class EditEntries extends Response{
	public List<Entry> entries = new ArrayList<Entry>();
	public EditEntries(
			WSHandler user, 
			List<String> templateCache
		){
		this.setTemplateId("EdEns");
		this.setPageId("EditEntrs");
		if(!templateCache.contains(this.getTemplateId())){
			this.setTemplate(this.getTemplate("./elements/entriesEdit.html"));
		}
		try {
			entries = Entry._find(Entry.class, "").exec(Entry.class);
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException
				| SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.setParent("#entries");
		Request r = new Request("get","editMap");
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		
		this.setAction("abot");
		this.setPageTitle("Synload Entries!");
	}
}
