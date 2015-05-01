package us.nxgencity.map.elements;

import java.util.List;

import us.nxgencity.map.sql.Entry;
import com.synload.framework.OOnPage;
import com.synload.framework.handlers.Request;
import com.synload.framework.handlers.Response;
import com.synload.framework.ws.WSHandler;

public class EntryPage extends Response{
	public Entry entry = null;
	
	public EntryPage(
			WSHandler user, 
			List<String> templateCache, 
			Entry e
		){
		String eId = String.valueOf(e.getId());
		OOnPage.add(user, "Entry", eId);
		this.entry = e;
		this.setTemplateId("EntP");
		this.setPageId("EntryPage");
		if(!templateCache.contains(this.getTemplateId())){
			this.setTemplate(this.getTemplate("./elements/index.html"));
		}
		this.setParent("none");
		Request r = new Request("get","entry");
			r.getData().put("entryId", eId);
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		
		this.setAction("cabot");
		this.setPageTitle(" Slide Entry!");
	}
}
