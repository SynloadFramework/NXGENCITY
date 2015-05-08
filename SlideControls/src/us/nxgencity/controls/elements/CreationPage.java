package us.nxgencity.controls.elements;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import us.nxgencity.map.sql.IconImage;

import com.synload.framework.handlers.Request;
import com.synload.framework.handlers.Response;
import com.synload.framework.sql.Model;
import com.synload.framework.ws.WSHandler;

public class CreationPage extends Response {
	public String x="",
			y="",
			presenter="", body = "", notes="", id="", title="";
	public List<IconImage> icons = new ArrayList<IconImage>();
	public CreationPage(
			WSHandler user,
			List<String> templateCache,
			String x,
			String y,
			String presenter
		){
		this.x=x;
		this.y=y;
		this.presenter=presenter;
		try {
			this.icons = Model._find(IconImage.class, "").exec(IconImage.class);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
		this.setTemplateId("creatP");
		this.setPageId("creatPage");
		if(!templateCache.contains(this.getTemplateId())){
			this.setTemplate(this.getTemplate("./elements/creationPage.html"));
		}
		this.setParent("#body");
		Request r = new Request("get","creationPage");
			r.getData().put("presenter", presenter);
			r.getData().put("x", x);
			r.getData().put("y", y);
		this.setRequest(r);
		//this.setParentTemplate("wrapper");
		this.setForceParent(false);
		
		this.setAction("cabot");
		this.setPageTitle(" Creation Page!");
	}
}
