package us.nxgencity.controls.elements;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import us.nxgencity.map.sql.Entry;
import us.nxgencity.map.sql.IconImage;

import com.synload.framework.handlers.Request;
import com.synload.framework.handlers.Response;
import com.synload.framework.sql.Model;
import com.synload.framework.ws.WSHandler;

public class EditPage extends Response {
	public int x=0, y=0;
	public String presenter="", body = "", notes="", title="", icon="";
	public long id=0;
	public List<IconImage> icons = new ArrayList<IconImage>();
	public EditPage(
			WSHandler user,
			List<String> templateCache,
			String id
		){
		Entry e;
		try {
			e = Entry._find(Entry.class, "`id`=?", id).exec(Entry.class).get(0);
			this.x=e.getX();
			this.y=e.getY();
			this.title=e.getTitle();
			this.id = e.getId();
			this.icon=e.getIcons();
			this.presenter=e.getPresenter();
			this.body = e.getBody().split("_NOTE_",-1)[0];
			this.notes = e.getBody().split("_NOTE_",-1)[1];
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			this.icons = Model._find(IconImage.class, "").exec(IconImage.class);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException e1) {
			e1.printStackTrace();
		}
		this.setTemplateId("editP");
		this.setPageId("editPage");
		if(!templateCache.contains(this.getTemplateId())){
			this.setTemplate(this.getTemplate("./elements/editPage.html"));
		}
		this.setParent("#body");
		Request r = new Request("get","editPage");
			r.getData().put("slideId", id);
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		
		this.setAction("cabot");
		this.setPageTitle(" Edit Page!");
	}
}
