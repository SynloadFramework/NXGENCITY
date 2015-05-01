package us.nxgencity.controls.pages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import us.nxgencity.controls.elements.CreationPage;
import us.nxgencity.controls.elements.SlideListings;
import us.nxgencity.map.events.LoadSlideEvent;
import us.nxgencity.map.sql.Entry;
import us.nxgencity.map.sql.IconImage;

import com.synload.eventsystem.EventPublisher;
import com.synload.eventsystem.events.RequestEvent;
import com.synload.framework.OOnPage;
import com.synload.framework.modules.annotations.Event;
import com.synload.framework.modules.annotations.Event.Type;

public class WSHandler {
	@Event(name="getSlideControls",description="Slide controls",trigger={"set","slide"},type=Type.WEBSOCKET)
	public void getSlideControls(RequestEvent event){
		String slideId = event.getRequest().getData().get("slideId");
		LoadSlideEvent lse = new LoadSlideEvent(slideId);
		EventPublisher.raiseEvent(lse, true, null);
	}
	@Event(name="getSlides",description="get slides",trigger={"get","control"},type=Type.WEBSOCKET)
	public void getSlides(RequestEvent event){
		String presenter = event.getRequest().getData().get("presenter");
		SlideListings slidelist = new SlideListings(
			event.getSession(),
			event.getRequest().getTemplateCache(),
			presenter
		);
		event.getSession().send(slidelist);
		OOnPage.add(event.getSession(), "Map", "1");
	}
	@Event(name="creationPage",description="get creation page",trigger={"get","creationPage"},type=Type.WEBSOCKET)
	public void creationPage(RequestEvent event){
		String x = event.getRequest().getData().get("x");
		String y = event.getRequest().getData().get("y");
		String presenter = event.getRequest().getData().get("presenter");
		CreationPage creationPage = new CreationPage(
			event.getSession(),
			event.getRequest().getTemplateCache(),
			x,
			y,
			presenter
		);
		event.getSession().send(creationPage);
	}
	@Event(name="createEntry",description="get creation page",trigger={"set","entry"},type=Type.WEBSOCKET)
	public void createEntry(RequestEvent event){
		String x = event.getRequest().getData().get("x");
		String y = event.getRequest().getData().get("y");
		String title = event.getRequest().getData().get("title");
		String presenter = event.getRequest().getData().get("presenter");
		String body = event.getRequest().getData().get("body");
		String icon = event.getRequest().getData().get("icon");
		Entry e = new Entry("x",x,"y",y,"presenter",presenter,"body",body, "title", title);
		try {
			e._insert();
			List<IconImage> ii = IconImage._find(IconImage.class, "`id`=?", icon).exec(IconImage.class);
			if(ii.size()>0){
				try {
					e._set(ii.get(0));
				} catch (NoSuchFieldException e1) {
					e1.printStackTrace();
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException
				| SQLException | InstantiationException | NoSuchMethodException | SecurityException | ClassNotFoundException | InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
}
