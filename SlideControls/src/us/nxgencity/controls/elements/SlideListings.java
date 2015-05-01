package us.nxgencity.controls.elements;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import us.nxgencity.map.sql.Entry;
import us.nxgencity.map.sql.IconImage;
import us.nxgencity.map.sql.SupportImage;

import com.synload.framework.handlers.Request;
import com.synload.framework.handlers.Response;
import com.synload.framework.sql.Model;
import com.synload.framework.ws.WSHandler;

public class SlideListings extends Response {
	public List<Entry> entries = new ArrayList<Entry>();
	public SupportImage support_image = null;
	public IconImage icon_image;
	
	public SlideListings(
			WSHandler user, 
			List<String> templateCache, 
			String presenter 
		){
		try {
			entries = Model._find(Entry.class, "`presenter`=?", presenter).exec(Entry.class);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		this.setTemplateId("EntList");
		this.setPageId("EntryListing");
		if(!templateCache.contains(this.getTemplateId())){
			this.setTemplate(this.getTemplate("./elements/listing.html"));
		}
		this.setParent("#body");
		Request r = new Request("get","control");
			r.getData().put("presenter", presenter);
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		
		this.setAction("cabot");
		this.setPageTitle(" Slide Controls!");
	}
}
