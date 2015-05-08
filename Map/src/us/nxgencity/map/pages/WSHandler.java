package us.nxgencity.map.pages;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import us.nxgencity.map.elements.Entries;
import us.nxgencity.map.elements.EntryPage;
import us.nxgencity.map.events.LoadSlideEvent;
import us.nxgencity.map.sql.Entry;
import com.synload.eventsystem.events.RequestEvent;
import com.synload.framework.OOnPage;
import com.synload.framework.modules.annotations.Event;
import com.synload.framework.modules.annotations.Event.Type;

public class WSHandler {
	@Event(name="getEventSlide",description="Individual slide for presentation",trigger={},type=Type.OTHER)
	public void getEventSlide(LoadSlideEvent event){
		String id = event.getEntryId();
		try {
			List<Entry> entries = Entry._find(Entry.class, "`id`=?", id).exec(Entry.class);
			if(entries.size()>0){
				Entry e = entries.get(0);
				List<com.synload.framework.ws.WSHandler> clients = OOnPage.getClients("Map", "1");
				for(com.synload.framework.ws.WSHandler client:clients){
					try {
						EntryPage ep = new EntryPage(
							client,
							new ArrayList<String>(),
							e
						);
						client.send(ep);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}else{
				System.out.println("ERROR: id not found! "+id);
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
	}
	@Event(name="getSlide",description="Get local single slide",trigger={"get","slide"},type=Type.WEBSOCKET)
	public void getSlide(RequestEvent event){
		String id = event.getRequest().getData().get("slideId");
		try {
			List<Entry> entries = Entry._find(Entry.class, "`id`=?", id).exec(Entry.class);
			if(entries.size()>0){
				Entry e = entries.get(0);
				event.getSession().send(new EntryPage(
						event.getSession(),
						new ArrayList<String>(),
						e
					));
			}else{
				System.out.println("ERROR: id not found! "+id);
			}
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | NoSuchMethodException
				| SecurityException | ClassNotFoundException
				| InvocationTargetException | SQLException e) {
			e.printStackTrace();
		}
	}
	@Event(name="getConnect",description="Connection Of Map",trigger={"get","connectMap"},type=Type.WEBSOCKET)
	public void getConnect(RequestEvent event){
		OOnPage.add(event.getSession(), "Map", "1");
		event.getSession().send(new Entries(event.getSession(),event.getRequest().getTemplateCache()));
	}

}
